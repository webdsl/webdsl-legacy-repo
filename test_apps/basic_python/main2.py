from main import *

def delete_all():
    for m in Message.all():
        m.delete()

print 'Content-type: text/plain'
print
print 'Now going to generate a message.'
delete_all()

m = Message(sender='Zef', message='Hoi!')
m.put()
Message(sender='Piet', message='Hello there!', reply_to=m).put()
print 'Now querying for all messages.'
for m in Message.all():
    if m.reply_to:
        print '%s: %s (in response to: %s)' % (m.sender, m.message, m.reply_to.sender)
    else:
        print '%s: %s' % (m.sender, m.message)
