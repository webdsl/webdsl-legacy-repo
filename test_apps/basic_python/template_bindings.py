from google.appengine.ext import db
import logging
import webdsl.querylist
import webdsl.db
class ParentTemplate(object):
    def __init__(self):
        self.scope = dict( )
    @property
    def template_bindings(self):
        from template import *
        tb = dict( )
        tb [ 'main' ] = Main
        tb [ 'head' ] = Head
        tb [ 'foot' ] = Foot
        tb [ 'body' ] = Body
        tb [ 'editEntryTemplate' ] = EditEntryTemplate
        tb [ 'addEntryTemplate' ] = AddEntryTemplate
        return tb