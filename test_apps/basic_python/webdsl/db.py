from google.appengine.ext import db
import logging

class Model(db.Model):
    def __init__(self, *params, **kparams):
        self._post_process_props = []
        self._putting = False
        db.Model.__init__(self, *params, **kparams)

    def put(self):
        #if self._putting:
            #return
        #print 'Saving %s' % self
        self._putting = True
        # Set counters
        for attr in self._post_process_props:
            setattr(self, attr+'_count', getattr(self, attr).item_count)
        db.Model.put(self)
        for attr in self._post_process_props:
            getattr(self, attr).inverse_prop_key = self.id
            getattr(self, attr).persist()
        import webdsl.querylist
        webdsl.querylist.query_counter += 1
        self._putting = False

    def __cmp__(self, other):
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
            return unicode(self.key())
        else:
            return None

    @classmethod
    def fetch_by_id(cls, id):
        if hasattr(cls, 'id'):
            return cls.all().filter("%s = " % cls.id, id).get()
        else:
            return cls.get_by_id(id)

    

