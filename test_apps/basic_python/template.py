from google.appengine.ext import db
import logging
import webdsl.querylist
import webdsl.db
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
        out.write( 'Skipped element.' )
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
        self.rh.redirect( '/home' + '' )
class Home(webdsl.utils.RequestHandler):
    def render(self):
        out = self.rh.response.out
        out.write( 'Hello world!' )
class Body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.rh.response.out
        out.write( 'Skipped element.' )
        out.write( 'Skipped element.' )
class ProcedureStatus(webdsl.utils.RequestHandler):
    def render(self):
        out = self.rh.response.out
        out.write( 'Skipped element.' )
class AllTasks(webdsl.utils.RequestHandler):
    def render(self):
        out = self.rh.response.out
        out.write( 'Skipped element.' )
        out.write( 'Skipped element.' )
        out.write( 'Skipped element.' )
webdsl.utils.register( '/viewMessage/([^/]+)' ,  ViewMessage ,  [ ( 'm1' , '' ,  long ,  data.Message ) ] )
webdsl.utils.register( '' ,  Home ,  [ ] )
webdsl.utils.register( '/procedureStatus/([^/]+)' ,  ProcedureStatus ,  [ ( 's1' , '' ,  long ,  data.ProcedureStatus ) ] )
webdsl.utils.register( '/allTasks' ,  AllTasks ,  [ ] )