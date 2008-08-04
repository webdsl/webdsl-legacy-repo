from google.appengine.ext import db
import logging
import webdsl.utils
import webdsl.db
import webdsl.querylist

class Message(webdsl.db.Model):
    inverse__User_messages = db.StringProperty() # Containing key
    recipients = webdsl.querylist.QuerySetProperty()
    text = db.TextProperty()

    def __repr__(self):
        return 'Message(recipients=%s, text=%s)' % (self.recipients, self.text)

class User(webdsl.db.Model):
    name = db.StringProperty()
    id_property = 'name'
    inverse__User_friends = db.StringListProperty() # Containing key

    # messages -> Set<Message> one to many
    _messages = None
    def get_messages(self):
        if not self._messages:
            self._post_process_props.append('messages')
            self._messages = webdsl.querylist.OneToManyDbQuerySet(self, 'Message', 'inverse__User_messages', self.id, self.messages_count)
        return self._messages
    def set_messages(self, value):
        # TODO: Remove old items
        for item in value:
            self.messages.append(item)
    messages = property(get_messages, set_messages)
    messages_count = db.IntegerProperty(default=0)

    # friends -> Set<User> many to many
    _friends = None
    def get_friends(self):
        if not self._friends:
            self._post_process_props.append('friends')
            self._friends = webdsl.querylist.ManyToManyDbQuerySet(self, 'User', 'inverse__User_friends', self.id, self.friends_count, 'friends')
        return self._friends
    def set_friends(self, value):
        # TODO: Remove old items
        for item in value:
            self.friends.append(item)
    friends = property(get_friends, set_friends)
    friends_count  = db.IntegerProperty(default=0)

    def __repr__(self):
        return 'User(name=%s)' % self.name

