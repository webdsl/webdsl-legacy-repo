from google.appengine.ext import db
import logging

op_to_filter = {'=': 'eq', '!=': 'neq', '<': 'lt', '<=': 'leq', '>': 'gt', '>=': 'geq'}

class Person(object):
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __repr__(self):
        return '(Name: %s, Age: %d)' % (self.name, self.age)

class QueryList(object):
    def __init__(self, lst):
        self.lst = lst
        self.filters = []
        self.order = None
        self.limit_ = 1000
        self.offset = 0

    def filter_eq(self, prop, val):
        c = self.copy()
        c.filters.append((prop, '=', val))
        return c

    def filter_neq(self, prop, val):
        c = self.copy()
        c.filters.append((prop, '!=', val))
        return c

    def filter_lt(self, prop, val):
        c = self.copy()
        c.filters.append((prop, '<', val))
        return c

    def filter_leq(self, prop, val):
        c = self.copy()
        c.filters.append((prop, '<=', val))
        return c

    def filter_gt(self, prop, val):
        c = self.copy()
        c.filters.append((prop, '>', val))
        return c

    def filter_geq(self, prop, val):
        self.filters.append((prop, '>=', val))
        c = self.copy()
        c.filters.append((prop, '>=', val))
        return c

    def order_by(self, prop):
        c = self.copy()
        c.order = prop
        return c

    def limit(self, limit, offset=0):
        c = self.copy()
        c.limit_ = limit
        c.offset = offset
        return c

    def list(self):
        lst = self.lst[:]
        for prop, op, val in self.filters:
            if op == '=':
                lst = filter(lambda o: getattr(o, prop) == val, lst)
            elif op == '!=':
                lst = filter(lambda o: getattr(o, prop) != val, lst)
            elif op == '<':
                lst = filter(lambda o: getattr(o, prop) < val, lst)
            elif op == '<=':
                lst = filter(lambda o: getattr(o, prop) <= val, lst)
            elif op == '>':
                lst = filter(lambda o: getattr(o, prop) > val, lst)
            elif op == '>=':
                lst = filter(lambda o: getattr(o, prop) >= val, lst)
        if self.order:
            descending = False
            prop = self.order
            if prop.startswith('-'):
                descending = True
                prop = prop[1:]
            if not descending:
                lst.sort(lambda x, y: cmp(getattr(x, prop), getattr(y, prop)))
            else:
                lst.sort(lambda x, y: cmp(getattr(y, prop), getattr(x, prop)))
        return lst[self.offset:self.offset+self.limit_]

    def append(self, item):
        self.lst.append(item)

    def remove(self, item):
        self.lst.remove(item)

    def copy(self):
        c = QueryList(self.lst)
        c.filters = self.filters[:]
        c.order = self.order
        c.limit_ = self.limit_
        c.offset = self.offset
        return c

    def __repr__(self):
        return repr(self.list())
    
    def __iter__(self):
        return iter(self.list())

class OneToManyDbQueryList(QueryList):
    """Database version of QueryList"""
    def __init__(self, type, inverse_prop, inverse_prop_key=None):
        QueryList.__init__(self, [])
        self.type = type
        self.inverse_prop = inverse_prop
        self.inverse_prop_key = inverse_prop_key
        self.append_list = []
        self.remove_list = []
        if inverse_prop_key:
            self.query = type.all().filter("%s =" % inverse_prop, inverse_prop_key)
        else:
            self.query_list = QueryList([])

    def append(self, item):
        self.append_list.append(item)

    def remove(self, item):
        self.remove_list.remove(item)

    def list(self):
        if self.inverse_prop_key:
            for prop, op, val in self.filters:
                self.query.filter('%s %s' % (prop, op), val)
            if self.order:
                self.query.order_by(self.order)
            self.query_list = QueryList(list(self.query.fetch(self.limit_ + len(self.remove_list), self.offset)))

        if not self.inverse_prop_key or self.append_list or self.remove_list:
            for item in self.append_list:
                self.query_list.append(item)
            for prop, op, val in self.filters:
                self.query_list = getattr(self.query_list, 'filter_%s' % op_to_filter[op])(prop, val)
            if self.order:
                self.query_list = self.query_list.order_by(self.order)

        return self.query_list.limit(self.limit_, self.offset).list()

    def perform_post_put(self):
        '''We now have a key, put it in all the appended items!'''
        for item in self.append_list:
            setattr(item, self.inverse_prop, self.inverse_prop_key)
            item.put() # Have to this one put
        for item in self.remove_list:
            setattr(item, self.inverse_prop, None)
            item.put() # Have to this one put
        self.append_list = []
        self.remove_list = []

    def copy(self):
        c = OneToManyDbQueryList(self.type, self.inverse_prop, self.inverse_prop_key)
        c.filters = self.filters[:]
        c.order = self.order
        c.limit_ = self.limit_
        c.offset = self.offset
        c.append_list = self.append_list
        c.remove_list = self.remove_list
        return c


class OneToManyListProperty(db.Property):
    def __init__(self, type, inverse_prop, *params, **kparams):
        self.type = type
        self.inverse_prop = inverse_prop
        db.Property.__init__(self, *params, **kparams)

    def __get__(self, model_instance, model_class):
        if model_instance is None:
            return self
        if not hasattr(model_instance, self._attr_name()) or not getattr(model_instance, self._attr_name()):
            model_instance._post_process_props.append(self.name)
            setattr(model_instance, self._attr_name(), OneToManyDbQueryList(self.type, self.inverse_prop, unicode(model_instance.key()) if model_instance.is_saved() else None))
        return getattr(model_instance, self._attr_name())

    def __set__(self, model_instance, value_list):
        if model_instance is None:
            return
        logging.info("%s.%s: %s" % (model_instance, self.inverse_prop, value_list))
        if value_list:
            # TODO: Remove old items
            for item in value_list:
                getattr(model_instance, self.name).append(item)

    def get_value_for_datastore(self, model_instance):
        return None

    def make_value_from_datastore(self, value):
        return []

    data_type = str
