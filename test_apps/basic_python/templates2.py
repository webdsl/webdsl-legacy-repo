import data
import webdsl.utils

###### Simulate templates
class Main(webdsl.utils.RequestHandler):
    def handle(self, rh):
        out = rh.response.out
        out.write('<html><body>')
        self.template_bindings['body'](self).handle(rh)
        out.write('</body></html>')


class Body(webdsl.utils.RequestHandler):
    def handle(self, rh):
        out = rh.response.out
        out.write('Default body!')

class Vmessage(webdsl.utils.RequestHandler):
    def handle(self, rh):
        out = rh.response.out
        out.write('<p>%s: %s</p>' % (self.scope['message'].sender, self.scope['message'].message))

class SayIt(webdsl.utils.RequestHandler):
    class Body(webdsl.utils.RequestHandler):
        def handle(self, rh):
            out = rh.response.out
            m = data.Message(sender='Jan', message='Hello there!')
            self.template_bindings['vmessage'](self, message=m).handle(rh)
            out.write(self.scope['msg'])

    def prepare_templates(self):
        self.template_bindings['body'] = SayIt.Body

    def handle(self, rh):
        out = rh.response.out
        self.template_bindings['main'](self).handle(rh)

webdsl.utils.register('/sayit/(.+)', SayIt, [('msg', None, str)])

###############

def delete_all():
    try:
        for m in data.Message.all():
            m.delete()
    except:
        pass

class Home2(webdsl.utils.RequestHandler):
    def handle(self, rh):
        out = rh.response.out
        delete_all()
        out.write('''Now going to generate a message.<br/>''')

        m = data.Message(sender='Zef', message='Hoi!')
        m.put()
        data.Message(sender='Piet', message='Hello there!', reply_to=m).put()

        out.write('''Now querying for all messages.<ul>''')
        for m in data.Message.all():
            if m.reply_to:
                out.write('''<li>%s: %s (in response to: %s)</li>''' % (m.sender, m.message, m.reply_to.sender))
            else:
                out.write('''<li>%s: <a href="/message/%d">%s</a></li>''' % (m.sender, m.key().id(), m.message))
        out.write('</ol>')
        # Template call
        self.template_bindings['main'](self.template_bindings).handle(rh)

webdsl.utils.register('/2', Home2, [])

class ViewMessage(webdsl.utils.RequestHandler):
    def handle(self, rh, message):
        out = rh.response.out
        out.write("%s: %s" % (message.sender, message.message))
webdsl.utils.register('/message/(.+)', ViewMessage, [('message', long, data.Message)])

class StandardMessage(webdsl.utils.RequestHandler):
    def handle(self, rh, message):
        out = rh.response.out
        out.write(message)
webdsl.utils.register('/msg/(.+)', StandardMessage, [('message', None, str)])

