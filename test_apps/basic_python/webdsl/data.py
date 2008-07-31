from google.appengine.ext import db

class Model(db.Model):
    def __init__(self, *params, **kparams):
        db.Model.__init__(self, *params, **kparams)
        self._post_process_props = []

    def put(self):
        db.Model.put(self)
        for attr in self._post_process_props:
            getattr(self, attr).inverse_prop_key = unicode(self.key())
            getattr(self, attr).perform_post_put()

    def __cmp__(self, other):
        if self.is_saved() and other.is_saved():
            return cmp(str(self.key()), str(other.key()))
        else:
            return db.Model.__cmp__(self, other)


#    @classmethod
#    def init_model(cls):
#        cls.attribute_types = {}
#        for attr in dir(cls):
#            if isinstance(getattr(cls, attr), db.Property):
#                cls.attribute_types[attr] = getattr(cls, attr).datastore_type()

    @classmethod
    def fetch_by_id(cls, id):
        if hasattr(cls, 'id'):
            return cls.all().filter("%s = " % cls.id, id).get()
        else:
            return cls.get_by_id(id)

    

