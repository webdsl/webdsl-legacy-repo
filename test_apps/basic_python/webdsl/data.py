from google.appengine.ext import db
import logging

class Model(db.Model):
    def __init__(self, *params, **kparams):
        self._post_process_props = []
        logging.info('Created and initted object: %s' % self)
        logging.info('-------------------')
        db.Model.__init__(self, *params, **kparams)

    def put(self):
        db.Model.put(self)
        for attr in self._post_process_props:
            getattr(self, attr).inverse_prop_key = unicode(self.key())
            getattr(self, attr).perform_post_put()
        import webdsl.querylist
        webdsl.querylist.query_counter += 1

    def __cmp__(self, other):
        if self.is_saved() and other.is_saved():
            return cmp(str(self.key()), str(other.key()))
        else:
            result = cmp(hash(self), hash(other))
            return cmp(hash(self), hash(other))


    @classmethod
    def fetch_by_id(cls, id):
        if hasattr(cls, 'id'):
            return cls.all().filter("%s = " % cls.id, id).get()
        else:
            return cls.get_by_id(id)

    

