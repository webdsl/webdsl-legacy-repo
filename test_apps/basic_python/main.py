from google.appengine.ext import db
import webdsl.data
import webdsl.utils
import webdsl.querylist
from datetime import datetime
#import data
#import template

#main = webdsl.utils.run

#if __name__ == '__main__':
    #main()

class Message(webdsl.data.Model):
    inverse__User_messages = db.StringProperty() # Containing key
    to = db.StringProperty()
    text = db.TextProperty()

    def __repr__(self):
        return 'Message(to=%s, text=%s)' % (self.to, self.text)

class User(webdsl.data.Model):
    name = db.StringProperty()
    messages = webdsl.querylist.OneToManyListProperty(Message, 'inverse__User_messages')
    id = 'name'

print 'Content-type: text/plain'
print
print 'Deleting old junk...'
t = datetime.now()
print 'Hoi!'
for u in User.all():
    u.delete()
for m in Message.all():
    m.delete()
print datetime.now() - t
print 'Ok, ready? Go!'

print 'Making a new object, adding a bunch of messages...'
t = datetime.now()
u = User()
u.name = 'Eelco'
u.messages.append(Message(text='Hallo allemaal!', to='Zef'))
m = Message(text='Goede morgen!', to='Zef')
u.messages.append(m)
u.messages.append(Message(text='Smoi!', to='Danny'))
print 'Before removing:'
print u.messages
u.messages.remove(m)
print 'After removing:'
print u.messages
print 'Re-adding...'
u.messages.append(m)
print u.messages
print datetime.now() - t

print 'Querying the in-memory list...'
t = datetime.now()
print u.messages.filter_eq('to', 'Zef')
print datetime.now() - t

print 'Persisting the object...'
t = datetime.now()
u.put()
print datetime.now() - t

print 'Querying it in the database...'
t = datetime.now()
print u.messages.filter_eq('to', 'Zef')
print datetime.now() - t

print 'Adding a temporary message and query the hybrid of database and in-memory...'
t = datetime.now()
u.messages.append(Message(text='Nieuwe bericht, niet in DB.', to='Zef'))
print u.messages.filter_eq('to', 'Zef')
print len(u.messages)
print datetime.now() - t

print "And now, let us query all..."
for m in webdsl.querylist.AllDbQueryList(Message).filter_eq('to', 'Zef'):
    print m

print 'Total of %d queries.' % webdsl.querylist.query_counter
