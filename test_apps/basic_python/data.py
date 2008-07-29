from google.appengine.ext import db
import logging
import webdsl.utils
import webdsl.data
class Message(webdsl.data.Model):
    reply_to = db.ReferenceProperty( )
    date = db.DateTimeProperty( auto_now_add = True )
    message = db.TextProperty( )
    sender = db.StringProperty( )