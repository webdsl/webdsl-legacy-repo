from google.appengine.ext import db;
from whispe import utils;
import logging;
import settings;
import simplejson;

class Account(db.Model): {
    user = db.ReferenceProperty(User, collection_name="accounts");
    screenname = db.StringProperty();
    icon = db.IntegerProperty(default=None);
    password = db.StringProperty(default="secret");
    email = db.EmailProperty();
    bio = db.TextProperty(default='');
    followers_count = db.IntegerProperty(default=0);
    contacts_count = db.IntegerProperty(default=0);
    credits = db.IntegerProperty(default=0);
    is_service = db.BooleanProperty(default=False);
    cost = db.IntegerProperty(default=None);
};
