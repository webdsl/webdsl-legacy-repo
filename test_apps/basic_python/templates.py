import data
import cgi
import webdsl.utils

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

    def render(self):
        out = self.rh.response.out

        # Start of form
        out.write('<form method="POST">')
        form_id = webdsl.utils.generateFormHash(self.scope, self)
        #current_form_submitted = self.rh.request.get('form_id') == form_id
        if self.rh.request.get('form_id') == form_id:
            self.data_bind()
        out.write('<input type="hidden" name="form_id" value="%s"/>' % form_id)
        #
        out.write('<input type="text" name="message__sender" value="')
        out.write(cgi.escape(self.scope['message'].sender, True))
        out.write('"/>')
        out.write(': ')
        out.write('<input type="text" name="message__message" value="')
        out.write(cgi.escape(self.scope['message'].message, True))
        out.write('"/>')
        out.write('<input type="submit" name="action-1" value="Save"/>')
        if self.rh.request.get('action-1'):
            self.do_save()

        # End of form
        out.write('</form>')
        #

class SayIt(webdsl.utils.RequestHandler):
    class Body(webdsl.utils.RequestHandler):
        def render(self):
            out = self.rh.response.out
            #m = data.Message.fetch_by_id(50)
            for m in data.Message.all():
                self.template_bindings['vmessage'](self, self.rh, message=m).render()
            #self.template_bindings['vmessage'](self, message=m).render()
            out.write(self.scope['msg'])

    def prepare_templates(self):
        self.template_bindings['body'] = SayIt.Body

    def render(self):
        out = self.rh.response.out
        self.template_bindings['main'](self, self.rh).render()

webdsl.utils.register('/sayit/(.+)', SayIt, [('msg', None, str)])

