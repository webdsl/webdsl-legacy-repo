from google.appengine.ext import db
import logging

class Model(db.Model):
    def __init__(self, *params, **kparams):
        self._post_process_props = []
        list_props = filter(lambda attr: isinstance(getattr(self.__class__, attr), db.ListProperty), dir(self.__class__))
        for attr in list_props:
            if kparams.has_key(attr) and kparams[attr] == None:
                kparams[attr] = []
        db.Model.__init__(self, *params, **kparams)
        non_google_properties = filter(lambda attr: not isinstance(getattr(self.__class__, attr), db.Property), kparams.keys())
        for attr in non_google_properties:
            setattr(self, attr, kparams[attr])

    def put(self):
        for attr in self._post_process_props:
            setattr(self, attr+'_count', getattr(self, attr).item_count)
        db.Model.put(self)
        for attr in self._post_process_props:
            getattr(self, attr).inverse_prop_key = self.id
            getattr(self, attr).persist()
        import webdsl.querylist
        webdsl.querylist.query_counter += 1

    def __cmp__(self, other):
        if not other:
            return False
        if self.is_saved() and other.is_saved():
            return cmp(str(self.key()), str(other.key()))
        else:
            result = cmp(hash(self), hash(other))
            return cmp(hash(self), hash(other))

    id_property = None

    @property
    def id(self):
        if self.id_property:
            return getattr(self, self.id_property)
        elif self.is_saved():
            return self.key().id()
        else:
            return None

    # Temporary
    @property
    def name(self):
        return self.id

    @classmethod
    def fetch_by_id(cls, id):
        if cls.id_property:
            return cls.all().filter("%s = " % cls.id_property, id).get()
        else:
            return cls.get_by_id(id)

    

