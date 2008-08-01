from google.appengine.ext import db
import logging

op_to_filter = {'=': 'eq', '!=': 'neq', '<': 'lt', '<=': 'leq', '>': 'gt', '>=': 'geq'}

query_counter = 0

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
        if item in self.lst:
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

    def __len__(self):
        if not self.filters:
            return len(self.lst)
        else:
            return len(self.list())

class OneToManyDbQueryList(QueryList):
    """Database version of QueryList"""
    def __init__(self, type, inverse_prop, inverse_prop_key=None):
        QueryList.__init__(self, [])
        if isinstance(type, basestring):
            import data # Assume it's from there
            type = getattr(data, type)
        self.type = type
        self.item_count = 0
        self.inverse_prop = inverse_prop
        self.inverse_prop_key = inverse_prop_key
        self.append_list = []
        self.remove_list = []
        self.query_list = QueryList([])

    def append(self, item):
        self.item_count += 1
        self.append_list.append(item)

    def remove(self, item):
        self.item_count -= 1
        if item in self.append_list:
            self.append_list.remove(item)
        else:
            self.remove_list.append(item)

    def list(self):
        query_list = QueryList(self.query_list.lst[:])
        if self.inverse_prop_key:
            self.query = self.type.all().filter("%s =" % self.inverse_prop, self.inverse_prop_key)
            for prop, op, val in self.filters:
                self.query.filter('%s %s' % (prop, op), val)
            if self.order:
                self.query.order_by(self.order)
            query_list = QueryList(list(self.query.fetch(self.limit_ + len(self.remove_list), self.offset)))
            global query_counter
            query_counter += 1

        if not self.inverse_prop_key or self.append_list or self.remove_list:
            for item in self.append_list:
                query_list.append(item)
            for item in self.remove_list:
                query_list.remove(item)
            for prop, op, val in self.filters:
                query_list = getattr(query_list, 'filter_%s' % op_to_filter[op])(prop, val)
            if self.order:
                query_list = query_list.order_by(self.order)

        return query_list.limit(self.limit_, self.offset).list()

    def persist(self):
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
        c = self.__class__(self.type, self.inverse_prop, self.inverse_prop_key)
        c.filters = self.filters[:]
        c.order = self.order
        c.limit_ = self.limit_
        c.offset = self.offset
        c.append_list = self.append_list
        c.remove_list = self.remove_list
        return c

    def __len__(self):
        if not self.filters:
            return self.item_count
        else:
            return len(self.list())


class ManyToManyDbQueryList(OneToManyDbQueryList):
    """Database version of QueryList"""

    def persist(self):
        '''We now have a key, put it in all the appended items!'''
        for item in self.append_list:
            if not self.inverse_prop_key in getattr(item, self.inverse_prop):
                getattr(item, self.inverse_prop).append(self.inverse_prop_key)
                item.put() # Have to this one put
        for item in self.remove_list:
            getattr(item, self.inverse_prop).remove(self.inverse_prop_key)
            item.put() # Have to this one put
        self.append_list = []
        self.remove_list = []

class AllDbQueryList(QueryList):
    """Database version of QueryList"""
    def __init__(self, type):
        QueryList.__init__(self, [])
        self.type = type
        self.append_list = []
        self.remove_list = []
        self.query = type.all()

    def append(self, item):
        self.append_list.append(item)

    def remove(self, item):
        self.item_count -= 1
        if item in self.append_list:
            self.append_list.remove(item)
        else:
            self.remove_list.append(item)

    def list(self):
        for prop, op, val in self.filters:
            self.query.filter('%s %s' % (prop, op), val)
        if self.order:
            self.query.order_by(self.order)
        self.query_list = QueryList(list(self.query.fetch(self.limit_ + len(self.remove_list), self.offset)))
        global query_counter
        query_counter += 1

        if self.append_list or self.remove_list:
            for item in self.append_list:
                self.query_list.append(item)
            for prop, op, val in self.filters:
                self.query_list = getattr(self.query_list, 'filter_%s' % op_to_filter[op])(prop, val)
            if self.order:
                self.query_list = self.query_list.order_by(self.order)

        return self.query_list.limit(self.limit_, self.offset).list()

    def copy(self):
        c = AllDbQueryList(self.type)
        c.filters = self.filters[:]
        c.order = self.order
        c.limit_ = self.limit_
        c.offset = self.offset
        c.append_list = self.append_list
        c.remove_list = self.remove_list
        return c

