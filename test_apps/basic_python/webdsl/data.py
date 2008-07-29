from google.appengine.ext import db

class Model(db.Model):
    @classmethod
    def init_model(cls):
        cls.attribute_types = {}
        for attr in dir(cls):
            if isinstance(getattr(cls, attr), db.Property):
                cls.attribute_types[attr] = getattr(cls, attr).datastore_type()

    @classmethod
    def fetch_by_id(cls, id_name, id):
        if id_name:
            return cls.all().filter("%s = " % id_name, id).get()
        else:
            return cls.get_by_id(id)

#class IdProperty(db.Property):
#    def __init__(self, id_field=None):
#        self.id_field = id_field
#
#    def get_value_for_datastore(self, model_instance):
#        return getattr(model_instance, self.id_field)
#
#    def make_value_from_datastore(self, value):
#        return simplejson.loads(value)
#
#    def datastore_type(self):
#        return db.Text

