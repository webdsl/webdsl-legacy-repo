from google.appengine.ext import db
import logging
import webdsl.querylist
import webdsl.db
from webdsl.utils import new_line
import data
import cgi
import md5
class User(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( 'User' )
    def prepare_templates(self):
        pass
class EditAll(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        self.template_bindings['main'](self, self.rh, out).render( )
    class Body(webdsl.utils.RequestHandler):
        def render(self):
            out = self.out
            out.write( '<form method="POST">' )
            form_id = webdsl.utils.generateFormHash( self.scope ,  self )
            is_submitted_form = self.rh.request.get( 'form_id' ) == form_id
            out.write( '<input type="hidden" name="form_id" value="%s"/>' % form_id )
            out.write( '<table>' )
            for m7 in webdsl.querylist.AllDbQuerySet( data.Message ) :
                out.write( '<tr>' )
                out.write( '<td>' )
                out.write( '<a href="' + '/user' + '/' + str( m7.sender.username ) + '' + '">' )
                out.write( m7.sender.name )
                out.write( new_line( ) )
                out.write( '</a>' )
                out.write( '</td>' )
                out.write( new_line( ) )
                out.write( '<td>' )
                field_id = webdsl.utils.generateUniqueFieldName( m7 ,  'm7.message' ,  self )
                if is_submitted_form :
                    m7.message = self.rh.request.get( field_id )
                out.write( '<textarea name="' + field_id + '">' )
                out.write( cgi.escape( m7.message ,  True ) )
                out.write( '</textarea>' )
                out.write( '</td>' )
                out.write( new_line( ) )
                out.write( '</tr>' )
                out.write( new_line( ) )
            out.write( new_line( ) )
            out.write( '</table>' )
            out.write( '<input type="submit" name="action-' + '1' + '" value="' + cgi.escape( 'Save' ,  True ) + '"/>' )
            if self.rh.request.get( 'action-' + '1' ) and is_submitted_form :
                self.do_save( )
            out.write( '</form>' )
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = EditAll.Body
class Body(webdsl.utils.RequestHandler):
    def do_save(self):
        self.rh.redirect( '/' + '' )
    def do_save(self):
        self.scope['m6'].put( )
        self.rh.redirect( '/' + '' )
    def render(self):
        out = self.out
        out.write( 'Nothin' )
class EditMessage(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        self.template_bindings['main'](self, self.rh, out).render( )
    class Body(webdsl.utils.RequestHandler):
        def render(self):
            out = self.out
            out.write( '<form method="POST">' )
            form_id = webdsl.utils.generateFormHash( self.scope ,  self )
            is_submitted_form = self.rh.request.get( 'form_id' ) == form_id
            out.write( '<input type="hidden" name="form_id" value="%s"/>' % form_id )
            field_id = webdsl.utils.generateUniqueFieldName( self.scope [ 'm6' ] ,  'm6.message' ,  self )
            if is_submitted_form :
                self.scope['m6'].message = self.rh.request.get( field_id )
            out.write( '<textarea name="' + field_id + '">' )
            out.write( cgi.escape( self.scope['m6'].message ,  True ) )
            out.write( '</textarea>' )
            out.write( '<input type="submit" name="action-' + '1' + '" value="' + cgi.escape( 'Save' ,  True ) + '"/>' )
            if self.rh.request.get( 'action-' + '1' ) and is_submitted_form :
                self.do_save( )
            out.write( '</form>' )
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = EditMessage.Body
class Home(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        from StringIO import StringIO
        old_out = out
        out = StringIO( )
        out.write( 'Home!' )
        out.write( new_line( ) )
        self.title = out.getvalue( )
        out = old_out
        self.template_bindings['main'](self, self.rh, out).render( )
    class Body(webdsl.utils.RequestHandler):
        def render(self):
            out = self.out
            out.write( '<div class="' + 'section1' + '">' )
            out.write( '<' + 'h1' + '>' )
            out.write( 'Test!' )
            out.write( new_line( ) )
            out.write( '</' + 'h1' + '>' )
            out.write( new_line( ) )
            out.write( '<p>' )
            out.write( 'Here you see all messages.' )
            out.write( new_line( ) )
            out.write( '</p>' )
            out.write( new_line( ) )
            out.write( '</div>' )
            out.write( '<table>' )
            out.write( '<tr>' )
            out.write( '<th>' )
            out.write( 'Sender' )
            out.write( '</th>' )
            out.write( new_line( ) )
            out.write( '<th>' )
            out.write( 'Message' )
            out.write( '</th>' )
            out.write( new_line( ) )
            out.write( '<th>' )
            out.write( 'Action' )
            out.write( '</th>' )
            out.write( new_line( ) )
            out.write( '</tr>' )
            out.write( new_line( ) )
            for m5 in webdsl.querylist.AllDbQuerySet( data.Message ) :
                out.write( '<tr>' )
                out.write( '<td>' )
                out.write( m5.sender.username )
                out.write( '</td>' )
                out.write( new_line( ) )
                out.write( '<td>' )
                out.write( cgi.escape( m5.message ,  True ) )
                out.write( '</td>' )
                out.write( new_line( ) )
                out.write( '<td>' )
                out.write( '<a href="' + '/editMessage' + '/' + str( m5.key().id( ) ) + '' + '">' )
                out.write( 'Edit' )
                out.write( new_line( ) )
                out.write( '</a>' )
                out.write( '</td>' )
                out.write( new_line( ) )
                out.write( '</tr>' )
                out.write( new_line( ) )
            out.write( new_line( ) )
            out.write( '</table>' )
            out.write( '<a href="' + '/' + '' + '">' )
            out.write( 'Home' )
            out.write( new_line( ) )
            out.write( '</a>' )
            out.write( '|' )
            out.write( '<a href="' + '/editAll' + '' + '">' )
            out.write( 'Edit all' )
            out.write( new_line( ) )
            out.write( '</a>' )
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = Home.Body
class Foot(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<hr/>' )
        out.write( '<p>' )
        out.write( 'Copyright Zef Hemel, 2008' )
        out.write( new_line( ) )
        out.write( '</p>' )
class Head(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<p>' )
        out.write( '<img src="' + 'http://webdsl.org/webdslorg/images/WebDSL-small.png' + '"/>' )
        out.write( new_line( ) )
        out.write( '</p>' )
        out.write( '<a href="' + '/' + '' + '">' )
        out.write( 'Home' )
        out.write( new_line( ) )
        out.write( '</a>' )
        out.write( '<hr/>' )
class Main(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        self.template_bindings['head'](self, self.rh, out).render( )
        self.template_bindings['body'](self, self.rh, out).render( )
        self.template_bindings['foot'](self, self.rh, out).render( )
class ProcedureStatus(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        self.template_bindings['main'](self, self.rh, out).render( )
        out.write( new_line( ) )
        out.write( new_line( ) )
    class Body(webdsl.utils.RequestHandler):
        def render(self):
            out = self.out
            out.write( '<' + 'h1' + '>' )
            out.write( self.scope['s1'].name )
            out.write( new_line( ) )
            out.write( '</' + 'h1' + '>' )
            out.write( '<table>' )
            out.write( '<tr>' )
            out.write( '<td>' )
            out.write( 'Name: ' )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '<td>' )
            out.write( self.scope['s1'].name )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '</tr>' )
            out.write( new_line( ) )
            out.write( '<tr>' )
            out.write( '<td>' )
            out.write( 'Enabled: ' )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '<td>' )
            out.write( self.scope['s1'].enabled )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '</tr>' )
            out.write( new_line( ) )
            out.write( '<tr>' )
            out.write( '<td>' )
            out.write( 'Date: ' )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( 'Skipped element: ' + 'TemplateCall("outputDateTime",[FieldAccess(Var("s1"),"date")],[])' )
            out.write( '</tr>' )
            out.write( new_line( ) )
            out.write( '<tr>' )
            out.write( '<td>' )
            out.write( 'Caller: ' )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '<td>' )
            out.write( '<a href="' + '/procedureStatus' + '/' + str( self.scope['s1'].caller.key().id( ) ) + '' + '">' )
            out.write( self.scope['s1'].caller.name )
            out.write( new_line( ) )
            out.write( '</a>' )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '</tr>' )
            out.write( new_line( ) )
            out.write( '<tr>' )
            out.write( '<td>' )
            out.write( 'Returnstate: ' )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '<td>' )
            out.write( self.scope['s1'].returnstate )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '</tr>' )
            out.write( new_line( ) )
            out.write( new_line( ) )
            out.write( '</table>' )
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = ProcedureStatus.Body
class AllTasks(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        from StringIO import StringIO
        old_out = out
        out = StringIO( )
        out.write( 'All tasks' )
        out.write( new_line( ) )
        self.title = out.getvalue( )
        out = old_out
        self.template_bindings['main'](self, self.rh, out).render( )
    class Body(webdsl.utils.RequestHandler):
        def render(self):
            out = self.out
            out.write( '<' + 'h1' + '>' )
            out.write( 'All tasks' )
            out.write( new_line( ) )
            out.write( '</' + 'h1' + '>' )
            out.write( '<div class="' + 'section1' + '">' )
            out.write( '</div>' )
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = AllTasks.Body
webdsl.utils.register( '/user/([^/]+)' ,  User ,  [ ( 'u1' , '' ,  unicode ,  data.User ) ] )
webdsl.utils.register( '/editAll' ,  EditAll ,  [ ] )
webdsl.utils.register( '/editMessage/([^/]+)' ,  EditMessage ,  [ ( 'm6' , '' ,  long ,  data.Message ) ] )
webdsl.utils.register( '/' ,  Home ,  [ ] )
webdsl.utils.register( '/procedureStatus/([^/]+)' ,  ProcedureStatus ,  [ ( 's1' , '' ,  long ,  data.ProcedureStatus ) ] )
webdsl.utils.register( '/allTasks' ,  AllTasks ,  [ ] )