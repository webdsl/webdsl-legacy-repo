import data
import cgi
import webdsl.utils
import logging
import md5

class Main(webdsl.utils.RequestHandler):
    def render(self):
        out = self.rh.response.out
        out.write('<html><body>')
        self.template_bindings['body'](self, self.rh).render()
        out.write('</body></html>')


class Body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.rh.response.out
        out.write('Default body!')

class Vmessage(webdsl.utils.RequestHandler):
    def initialize(self):
        pass

    def do_save(self):
        self.scope['message'].put()
        self.redirect_to_self()

    def do_delete(self):
        self.scope['message'].delete()
        self.redirect_to_self()

    def render(self):
        out = self.rh.response.out

        # Start of form
        out.write('<form method="POST">')
        form_id = webdsl.utils.generateFormHash(self.scope, self)
        is_submitted_form = self.rh.request.get('form_id') == form_id
        out.write('<input type="hidden" name="form_id" value="%s"/>' % form_id)
        
        # Input
        md5digest = md5.md5('message.sender').hexdigest()
        if is_submitted_form:
            self.scope['message'].sender = self.rh.request.get('sender-' + md5digest)
        out.write('<input type="text" name="sender-' + md5digest + '" value="')
        out.write(cgi.escape(self.scope['message'].sender, True))
        out.write('"/>')

        out.write(': ')

        # Input
        md5digest = md5.md5('message.message').hexdigest()
        if is_submitted_form:
            self.scope['message'].message = self.rh.request.get('message-' + md5digest)
        out.write('<input type="text" name="message-' + md5digest + '" value="')
        out.write(cgi.escape(self.scope['message'].message, True))
        out.write('"/>')

        # Action reference
        out.write('<input type="submit" name="action-1" value="Save"/>')
        if self.rh.request.get('action-1') and is_submitted_form:
            self.do_save()

        out.write('<input type="submit" name="action-2" value="Delete"/>')
        if self.rh.request.get('action-2') and is_submitted_form:
            self.do_delete()

        # End of form
        out.write("</form>\n")

class SayIt(webdsl.utils.RequestHandler):
    class Body(webdsl.utils.RequestHandler):
        def render(self):
            out = self.rh.response.out
            m = data.Message.fetch_by_id(50)
            for m in data.Message.all():
                self.template_bindings['vmessage'](self, self.rh, message=m).render()
            new_m = data.Message(sender='', message='')
            self.template_bindings['vmessage'](self, self.rh, message=new_m).render()
            out.write(self.scope['msg'])

    def prepare_templates(self):
        self.template_bindings['body'] = SayIt.Body

    def render(self):
        out = self.rh.response.out
        self.template_bindings['main'](self, self.rh).render()

webdsl.utils.register('/sayit/(.+)', SayIt, [('msg', None, str)])

