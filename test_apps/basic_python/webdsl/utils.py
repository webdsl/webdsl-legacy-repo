import wsgiref.handlers
import webdsl.data
import logging
import urllib
import template_bindings

from google.appengine.ext import webapp
from google.appengine.ext import db

mappings = []

def generateFormHash(data, template, level=0):
    h = ''
    if isinstance(data, dict):
        parts = []
        for key, value in data.items():
            parts.append('%s: %s' % (key, generateFormHash(value, template, level+1)))
        h = hash("\n".join(parts))
    elif isinstance(data, list):
        h = hash("\n".join([generateFormHash(e, template, level+1) for e in data]))
    elif isinstance(data, webdsl.data.Model):
        try:
            h = hash(str(data.key()))
        except:
            h = hash(data.__class__.__name__)
    else:
        h = hash(data)
    if level == 0:
        # Find form_counter
        original_template = template
        while not hasattr(template, 'form_counters'):
            template = template.parent
        if template.form_counters.has_key(h):
            template.form_counters[h] += 1
        else:
            template.form_counters[h] = 1
        return "%s-%s-%d" % (original_template.__class__.__name__, h, template.form_counters[h])
    else:
        return h

def register(path, cls, param_mappings=[]):
    global mappings
    class _cls(webapp.RequestHandler):
        def get(self, *params):
            o = cls(template_bindings.ParentTemplate(), self)
            o.form_counters = {} # Stores hashes => number of forms
            i = 0
            d = {}
            while i < len(params):
                (name, id_name, id_type, type) = param_mappings[i]
                param = urllib.unquote(params[i])
                if issubclass(type, webdsl.data.Model):
                    o.scope[name] = type.fetch_by_id(id_name, id_type(param))
                else:
                    o.scope[name] = type(param)
                i += 1
            o.init()
            o.render()
        def post(self, *params):
            self.get(*params)

    mappings.append((path, _cls))

class RequestHandler(object):
    def __init__(self, parent, rh, **scope):
        self.template_bindings = parent.template_bindings
        self.scope = parent.scope.copy()
        self.parent = parent
        self.rh = rh
        for key, value in scope.items():
            self.scope[key] = value

    def init(self):
        self.prepare_templates()
        self.initialize()

#    def data_bind(self):
#        for key, value in self.rh.request.params.items():
#            if '__' in key:
#                (arg, field) = key.split('__')
#                arg = str(arg)
#                setattr(self.scope[arg], field, self.scope[arg].attribute_types[arg](value))

    def redirect_to_self(self):
        self.rh.redirect(self.rh.request.path_info)

    def prepare_templates(self):
        pass

    def initialize(self):
        pass

    def render(self):
        pass

def run():
    application = webapp.WSGIApplication(mappings, debug=True)
    wsgiref.handlers.CGIHandler().run(application)

