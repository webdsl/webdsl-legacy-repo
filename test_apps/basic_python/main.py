from google.appengine.ext import db
import webdsl.db
import webdsl.utils
import webdsl.querylist
from datetime import datetime
from data import User, Message
#import template

#main = webdsl.utils.run

#if __name__ == '__main__':
    #main()


webdsl.querylist.query_counter = 0
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
u.messages.append(Message(text='Hallo allemaal!', recipients=['Zef']))
u.put()

m = Message(text='Goede morgen!', recipients=['Zef'])
u.messages.append(m)
u.messages.append(Message(text='Smoi!', recipients=['Danny']))
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
print u.messages.filter_eq('recipients', 'Zef')
print datetime.now() - t

print 'Persisting the object...'
t = datetime.now()
u.put()
print datetime.now() - t

print 'Querying it in the database...'
t = datetime.now()
print u.messages.filter_eq('recipients', 'Zef')
print datetime.now() - t

print 'Adding a temporary message and query the hybrid of database and in-memory...'
t = datetime.now()
u.messages.append(Message(text='Nieuwe bericht, niet in DB.', recipients='Zef'))
print u.messages.filter_eq('recipients', 'Zef')
print datetime.now() - t

print "And now, let us query all..."
for m in webdsl.querylist.AllDbQuerySet(Message).filter_eq('recipients', 'Zef'):
    print m

print '----------------- FRIEND STUFF -----------------------'
u2 = User()
u2.name = 'Danny'
u2.friends.append(u)
print "Eelco's friends: %s" % u.friends
print "Danny's friends: %s" % u2.friends
u2.put()
u.put()
print u.friends
print u2.friends
print '---------- String list property -------------'

print 'Total of %d queries.' % webdsl.querylist.query_counter
