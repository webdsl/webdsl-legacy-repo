from google.appengine.ext import db
import logging
import webdsl.querylist
import webdsl.db
from webdsl.utils import new_line
class Message(webdsl.db.Model):
    message = db.TextProperty( )
    date = db.DateTimeProperty( auto_now_add = True )
    _recipients = None
    def get_recipients(self):
        if self._recipients == None :
            self._post_process_props.append( 'recipients' )
            self._recipients = webdsl.querylist.ManyToManyDbQuerySet( self ,  'User' ,  'inverse__Message_recipients' ,  self.id ,  self.recipients_count ,  'receivedmessages' )
        return self._recipients
    def set_recipients(self, value):
        for item in value :
            self.recipients.append( item )
    recipients = property( get_recipients ,  set_recipients )
    recipients_count = db.IntegerProperty( default = 0 )
    _sender = None
    def get_sender(self):
        if self._sender == None :
            self._sender = User.fetch_by_id( self.inverse__User_messages )
        return self._sender
    def set_sender(self, value):
        self.inverse__User_messages = value.id
        self._sender = value
    sender = property( get_sender ,  set_sender )
    inverse__User_receivedmessages = db.ListProperty( unicode )
    inverse__User_messages = db.StringProperty( )
class User(webdsl.db.Model):
    inverse__Message_recipients = db.ListProperty( long )
    _receivedmessages = None
    def get_receivedmessages(self):
        if self._receivedmessages == None :
            self._post_process_props.append( 'receivedmessages' )
            self._receivedmessages = webdsl.querylist.ManyToManyDbQuerySet( self ,  'Message' ,  'inverse__User_receivedmessages' ,  self.id ,  self.receivedmessages_count ,  'recipients' )
        return self._receivedmessages
    def set_receivedmessages(self, value):
        for item in value :
            self.receivedmessages.append( item )
    receivedmessages = property( get_receivedmessages ,  set_receivedmessages )
    receivedmessages_count = db.IntegerProperty( default = 0 )
    _messages = None
    def get_messages(self):
        if self._messages == None :
            self._post_process_props.append( 'messages' )
            self._messages = webdsl.querylist.OneToManyDbQuerySet( self ,  'Message' ,  'inverse__User_messages' ,  self.id ,  self.messages_count ,  None )
        return self._messages
    def set_messages(self, value):
        for item in value :
            self.messages.append( item )
    messages = property( get_messages ,  set_messages )
    messages_count = db.IntegerProperty( default = 0 )
    username = db.StringProperty( )
    id_property = 'username'
class ProcedureStatus(webdsl.db.Model):
    returnstate = db.IntegerProperty( )
    caller = db.ReferenceProperty( )
    date = db.DateTimeProperty( auto_now_add = True )
    enabled = db.BooleanProperty( )