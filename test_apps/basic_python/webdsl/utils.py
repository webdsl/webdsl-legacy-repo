import wsgiref.handlers
import webdsl.data
import logging
import urllib
import template_bindings

from google.appengine.ext import webapp
from google.appengine.ext import db

mappings = []

def register(path, cls, param_mappings=[]):
    global mappings
    class _cls(webapp.RequestHandler):
        def get(self, *params):
            o = cls(template_bindings.ParentTemplate())
            i = 0
            d = {}
            while i < len(params):
                (name, id_type, type) = param_mappings[i]
                param = urllib.unquote(params[i])
                if issubclass(type, webdsl.data.Model):
                    o.scope[name] = type.fetch_by_id(id_type(param))
                else:
                    o.scope[name] = type(param)
                i += 1
            o.init()
            o.handle(self)
        def post(self, *params):
            self.get(*params)

    mappings.append((path, _cls))

class RequestHandler(object):
    def __init__(self, parent, **scope):
        self.template_bindings = parent.template_bindings
        self.scope = parent.scope.copy()
        for key, value in scope.items():
            self.scope[key] = value

    def init(self):
        self.prepare_templates()
        self.initialize()

    def prepare_templates(self):
        pass

    def initialize(self):
        pass

    def handle(self, rh):
        pass

def run():
    application = webapp.WSGIApplication(mappings, debug=True)
    wsgiref.handlers.CGIHandler().run(application)

