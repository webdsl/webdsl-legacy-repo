from google.appengine.ext import db
import webdsl.data
import logging

class Message(webdsl.data.Model):
    reply_to = db.ReferenceProperty()
    date = db.DateTimeProperty(auto_now_add=True)
    message = db.TextProperty()
    sender = db.StringProperty()

class User(webdsl.data.Model):
    screenname = db.StringProperty()
    password = db.StringProperty()
    id = screenname

    @property
    def name(self):
        return self.screenname
