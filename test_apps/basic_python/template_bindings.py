
class ParentTemplate(object):
    def __init__(self):
        self.scope = {}

    @property
    def template_bindings(self):
        from templates import *
        
        tb = {}
        tb['main'] = Main
        tb['body'] = Body
        tb['vmessage'] = Vmessage
        return tb

