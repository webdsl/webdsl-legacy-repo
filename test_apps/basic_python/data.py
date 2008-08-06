from google.appengine.ext import db
import logging
import webdsl.querylist
import webdsl.db
class Entry(webdsl.db.Model):
    message = db.TextProperty( default = '' )
    date = db.DateTimeProperty( auto_now_add = True )
    _sender = None
    def get_sender(self):
        if self._sender == None and self.inverse__User_messages :
            self._sender = User.fetch_by_id( self.inverse__User_messages )
        else:
            if self._sender == None :
                self._sender = User( )
        return self._sender
    def set_sender(self, value):
        self.inverse__User_messages = value.id
        self._sender = value
    sender = property( get_sender ,  set_sender )
    inverse__User_messages = db.StringProperty( default = '' )
class User(webdsl.db.Model):
    _messages = None
    def get_messages(self):
        if self._messages == None :
            self._post_process_props.append( 'messages' )
            self._messages = webdsl.querylist.OneToManyDbQuerySet( self ,  'Entry' ,  'inverse__User_messages' ,  self.id ,  self.messages_count ,  None )
        return self._messages
    def set_messages(self, value):
        for item in value :
            self.messages.append( item )
    messages = property( get_messages ,  set_messages )
    messages_count = db.IntegerProperty( default = 0 )
    username = db.StringProperty( default = '' )
    id_property = 'username'
class ProcedureStatus(webdsl.db.Model):
    returnstate = db.IntegerProperty( default = 0 )
    caller = db.ReferenceProperty( )
    date = db.DateTimeProperty( auto_now_add = True )
    enabled = db.BooleanProperty( default = False )