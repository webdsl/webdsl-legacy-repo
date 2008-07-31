from google.appengine.ext import db
import webdsl.data
import webdsl.utils
import webdsl.querylist
#import data
#import template

#main = webdsl.utils.run

#if __name__ == '__main__':
    #main()

class Message(webdsl.data.Model):
    user = db.StringProperty() # Containing key
    to = db.StringProperty()
    text = db.TextProperty()

class User(webdsl.data.Model):
    name = db.StringProperty()
    messages = webdsl.querylist.OneToManyListProperty(Message, 'user')
    id = 'name'

print 'Content-type: text/plain'
print
print 'Hoi!'
for u in User.all():
    u.delete()
for m in Message.all():
    m.delete()
print 'Ok, ready? Go!'

u = User()
u.name = 'Eelco'
u.messages.append(Message(text='Hallo allemaal!', to='Zef'))
u.messages.append(Message(text='Goede morgen!', to='Zef'))
u.messages.append(Message(text='Smoi!', to='Danny'))
print [m.text for m in u.messages.filter_eq('to', 'Danny')]
u.put()
print [m.text for m in u.messages.filter_eq('to', 'Danny')]
u.messages.append(Message(text='Nieuwe bericht, niet in DB.', to='Danny'))
print [m.text for m in u.messages.filter_eq('to', 'Danny')]

print 'And now let\'s query'
for u in User.all():
    print u.messages

