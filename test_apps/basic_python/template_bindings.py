from google.appengine.ext import db
import logging
import webdsl.querylist
import webdsl.db
import webdsl.markdown
class ParentTemplate(object):
    def __init__(self):
        self.scope = dict( )
    @property
    def template_bindings(self):
        import template
        tb = dict( )
        tb [ 'main' ] = template.Main
        tb [ 'head' ] = template.Head
        tb [ 'foot' ] = template.Foot
        tb [ 'body' ] = template.Body
        tb [ 'editEntryTemplate' ] = template.EditEntryTemplate
        tb [ 'addEntryTemplate' ] = template.AddEntryTemplate
        return tb