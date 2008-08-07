from google.appengine.ext import db
import logging
import webdsl.querylist
import webdsl.db
import webdsl.markdown
class Entry(webdsl.db.Model):
    someUser = webdsl.db.PartiallyInlinedReferenceProperty( 'User' ,  [ 'id' ,  'name' ,  'username' ] )
    message = db.TextProperty( default = '' )
    date = db.DateTimeProperty( auto_now_add = True )
    def get_sender(self):
        return self.inverse__User_entries_inline
    def set_sender(self, value):
        self.inverse__User_entries_inline = value
    sender = property( get_sender ,  set_sender )
    inverse__User_entries = db.StringProperty( default = '' )
    inverse__User_entries_inline = webdsl.db.PartiallyInlinedReferenceProperty( 'User' ,  [ 'id' ,  'name' ,  'username' ] )
class User(webdsl.db.Model):
    _entries = None
    def get_entries(self):
        if self._entries == None :
            self._post_process_props.append( 'entries' )
            self._entries = webdsl.querylist.OneToManyDbQuerySet( self ,  'Entry' ,  'inverse__User_entries' ,  self.id ,  self.entries_count ,  None )
        return self._entries
    def set_entries(self, value):
        for item in value :
            self.entries.append( item )
    entries = property( get_entries ,  set_entries )
    entries_count = db.IntegerProperty( default = 0 )
    username = db.StringProperty( default = '' )
    @property
    def name(self):
        return self.username
    id_property = 'username'
class ProcedureStatus(webdsl.db.Model):
    returnstate = db.IntegerProperty( default = 0 )
    caller = webdsl.db.PartiallyInlinedReferenceProperty( 'ProcedureStatus' ,  [ 'name' ,  'id' ] )
    date = db.DateTimeProperty( auto_now_add = True )
    enabled = db.BooleanProperty( default = False )
EntryProxy = webdsl.db.create_proxy_model( Entry )
UserProxy = webdsl.db.create_proxy_model( User )
ProcedureStatusProxy = webdsl.db.create_proxy_model( ProcedureStatus )