
class ParentTemplate(object):
    def __init__(self):
        self.scope = {}

    @property
    def template_bindings(self):
        from template import *
        
        tb = {}
        tb['main'] = Main
        tb['foot'] = Foot
        tb['head'] = Head
        return tb

