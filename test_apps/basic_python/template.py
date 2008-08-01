from google.appengine.ext import db
import logging
import webdsl.utils
import webdsl.data
import data
import cgi
import md5
class ViewMessage(webdsl.utils.RequestHandler):
    def render(self):
        out = self.rh.response.out
        out.write( '<form method="POST">' )
        form_id = webdsl.utils.generateFormHash( self.scope ,  self )
        is_submitted_form = self.rh.request.get( 'form_id' ) == form_id
        out.write( '<input type="hidden" name="form_id" value="%s"/>' % form_id )
        if is_submitted_form :
            self.scope['m1'].sender = self.rh.request.get( 'm1.sender' )
        out.write( '<input type="text" name="' + 'm1.sender' + '" value="' )
        out.write( cgi.escape( self.scope['m1'].sender ,  True ) )
        out.write( '"/>' )
        out.write( ':' )
        if is_submitted_form :
            self.scope['m1'].message = self.rh.request.get( 'm1.message' )
        out.write( '<textarea name="' + 'm1.message' + '">' )
        out.write( cgi.escape( self.scope['m1'].message ,  True ) )
        out.write( '</textarea>' )
        out.write( '<input type="submit" name="action-' + '1' + '" value="' + cgi.escape( 'Save' ,  True ) + '"/>' )
        if self.rh.request.get( 'action-' + '1' ) and is_submitted_form :
            self.do_save( )
        out.write( '</form>' )
    def do_save(self):
        self.scope['m1'].put( )
        self.rh.redirect( '/hello' + '' )
class Hello(webdsl.utils.RequestHandler):
    def render(self):
        out = self.rh.response.out
        out.write( 'Hello world!' )
        out.write( 'How are you doing\'s?' )
webdsl.utils.register( '/viewMessage/([^/]+)' ,  ViewMessage ,  [ ( 'm1' , '' ,  long ,  data.Message ) ] )
webdsl.utils.register( '/hello' ,  Hello ,  [ ] )