from google.appengine.ext import db
import logging
import webdsl.querylist
import webdsl.db
import webdsl.markdown
import data
import cgi
import md5
from webdsl.utils import new_line
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
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = EditAll_body
class EditAll_body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<form method="POST">' )
        form_id = webdsl.utils.generateFormHash( self.scope ,  self )
        is_submitted_form = self.rh.request.get( 'form_id' ) == form_id
        out.write( '<input type="hidden" name="form_id" value="%s"/>' % form_id )
        out.write( '<table>' )
        for m11 in webdsl.querylist.AllDbQuerySet( data.Entry ) :
            out.write( '<tr>' )
            out.write( '<td>' )
            out.write( '<a href="' + '/user' + '/' + str( m11.sender.username ) + '' + '">' )
            out.write( cgi.escape( m11.sender.name ,  True ) )
            out.write( new_line( ) )
            out.write( '</a>' )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '<td>' )
            field_id = webdsl.utils.generateUniqueFieldName( m11 ,  'm11.message' ,  self )
            if is_submitted_form :
                m11.message = self.rh.request.get( field_id )
            out.write( '<textarea name="' + field_id + '" class="inputText">' )
            out.write( cgi.escape( m11.message ,  True ) )
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
    def do_save(self):
        self.rh.redirect( '/' + '' )
class AddEntryTemplate(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<form method="POST">' )
        form_id = webdsl.utils.generateFormHash( self.scope ,  self )
        is_submitted_form = self.rh.request.get( 'form_id' ) == form_id
        out.write( '<input type="hidden" name="form_id" value="%s"/>' % form_id )
        out.write( '<table>' )
        out.write( '<tr>' )
        out.write( '<td>' )
        out.write( 'Sender: ' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '<td>' )
        out.write( '<div class="' + 'inputSimpleRefAssociation' + '">' )
        field_id = webdsl.utils.generateUniqueFieldName( self.scope [ 'm10' ] ,  'm10.sender' ,  self )
        if is_submitted_form :
            self.scope['m10'].sender = data.User.fetch_by_id( self.rh.request.get( field_id ) )
        out.write( '<select name="' + field_id + '">' )
        for item in webdsl.querylist.AllDbQuerySet( data.User ) :
            if self.scope['m10'].sender == item :
                out.write( '<option selected="selected" ' )
            else:
                out.write( '<option ' )
            out.write( 'value="' + cgi.escape( item.id ,  True ) + '">' )
            out.write( cgi.escape( item.name ,  True ) )
            out.write( '</option>' )
        out.write( '</select>' )
        out.write( new_line( ) )
        out.write( '</div>' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '</tr>' )
        out.write( new_line( ) )
        out.write( '<tr>' )
        out.write( '<td>' )
        out.write( 'Message: ' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '<td>' )
        field_id = webdsl.utils.generateUniqueFieldName( self.scope [ 'm10' ] ,  'm10.message' ,  self )
        if is_submitted_form :
            self.scope['m10'].message = self.rh.request.get( field_id )
        out.write( '<textarea name="' + field_id + '" class="inputText">' )
        out.write( cgi.escape( self.scope['m10'].message ,  True ) )
        out.write( '</textarea>' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '</tr>' )
        out.write( new_line( ) )
        out.write( '</table>' )
        out.write( '<input type="submit" name="action-' + '1' + '" value="' + cgi.escape( 'Add' ,  True ) + '"/>' )
        if self.rh.request.get( 'action-' + '1' ) and is_submitted_form :
            self.do_save( )
        out.write( '</form>' )
    def do_save(self):
        self.scope['m10'].put( )
        self.rh.redirect( '/' + '' )
class EditEntryTemplate(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<form method="POST">' )
        form_id = webdsl.utils.generateFormHash( self.scope ,  self )
        is_submitted_form = self.rh.request.get( 'form_id' ) == form_id
        out.write( '<input type="hidden" name="form_id" value="%s"/>' % form_id )
        out.write( '<table>' )
        out.write( '<tr>' )
        out.write( '<td>' )
        out.write( 'Sender: ' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '<td>' )
        out.write( '<div class="' + 'inputSimpleRefAssociation' + '">' )
        field_id = webdsl.utils.generateUniqueFieldName( self.scope [ 'm9' ] ,  'm9.sender' ,  self )
        if is_submitted_form :
            self.scope['m9'].sender = data.User.fetch_by_id( self.rh.request.get( field_id ) )
        out.write( '<select name="' + field_id + '">' )
        for item in webdsl.querylist.AllDbQuerySet( data.User ) :
            if self.scope['m9'].sender == item :
                out.write( '<option selected="selected" ' )
            else:
                out.write( '<option ' )
            out.write( 'value="' + cgi.escape( item.id ,  True ) + '">' )
            out.write( cgi.escape( item.name ,  True ) )
            out.write( '</option>' )
        out.write( '</select>' )
        out.write( new_line( ) )
        out.write( '</div>' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '</tr>' )
        out.write( new_line( ) )
        out.write( '<tr>' )
        out.write( '<td>' )
        out.write( 'Message: ' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '<td>' )
        field_id = webdsl.utils.generateUniqueFieldName( self.scope [ 'm9' ] ,  'm9.message' ,  self )
        if is_submitted_form :
            self.scope['m9'].message = self.rh.request.get( field_id )
        out.write( '<textarea name="' + field_id + '" class="inputText">' )
        out.write( cgi.escape( self.scope['m9'].message ,  True ) )
        out.write( '</textarea>' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '</tr>' )
        out.write( new_line( ) )
        out.write( '</table>' )
        out.write( '<input type="submit" name="action-' + '1' + '" value="' + cgi.escape( 'Save' ,  True ) + '"/>' )
        if self.rh.request.get( 'action-' + '1' ) and is_submitted_form :
            self.do_save( )
        out.write( '<input type="submit" name="action-' + '2' + '" value="' + cgi.escape( 'Delete' ,  True ) + '"/>' )
        if self.rh.request.get( 'action-' + '2' ) and is_submitted_form :
            self.do_delete( )
        out.write( '</form>' )
    def do_delete(self):
        self.scope['m9'].delete( )
        self.rh.redirect( '/' + '' )
    def do_save(self):
        self.scope['m9'].put( )
        self.rh.redirect( '/' + '' )
class EditEntry(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        self.template_bindings['main'](self, self.rh, out).render( )
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = EditEntry_body
class EditEntry_body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        self.template_bindings['editEntryTemplate'](self, self.rh, out, m9=self.scope['m8']).render( )
class Register(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        from StringIO import StringIO
        old_out = out
        out = StringIO( )
        out.write( 'Register' )
        out.write( new_line( ) )
        self.title = out.getvalue( )
        out = old_out
        self.template_bindings['main'](self, self.rh, out).render( )
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = Register_body
class Register_body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        self.scope [ 'user4' ] = data.User( )
        out.write( '<form method="POST">' )
        form_id = webdsl.utils.generateFormHash( self.scope ,  self )
        is_submitted_form = self.rh.request.get( 'form_id' ) == form_id
        out.write( '<input type="hidden" name="form_id" value="%s"/>' % form_id )
        out.write( '<p>' )
        out.write( 'Username: ' )
        out.write( new_line( ) )
        field_id = webdsl.utils.generateUniqueFieldName( self.scope [ 'user4' ] ,  'user4.username' ,  self )
        if is_submitted_form :
            self.scope['user4'].username = self.rh.request.get( field_id )
        out.write( '<input type="text" name="' + field_id + '" class="inputString" value="' )
        out.write( cgi.escape( self.scope['user4'].username ,  True ) )
        out.write( '"/>' )
        out.write( new_line( ) )
        out.write( '</p>' )
        out.write( '<input type="submit" name="action-' + '1' + '" value="' + cgi.escape( 'Register' ,  True ) + '"/>' )
        if self.rh.request.get( 'action-' + '1' ) and is_submitted_form :
            self.do_register( )
        out.write( '</form>' )
    def do_register(self):
        self.scope['user4'].put( )
        self.rh.redirect( '/' + '' )
class Home(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        from StringIO import StringIO
        old_out = out
        out = StringIO( )
        out.write( 'Guestbook' )
        out.write( new_line( ) )
        self.title = out.getvalue( )
        out = old_out
        self.template_bindings['main'](self, self.rh, out).render( )
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = Home_body
class Home_body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<table>' )
        out.write( '<tr>' )
        out.write( '<th>' )
        out.write( 'Sender' )
        out.write( '</th>' )
        out.write( new_line( ) )
        out.write( '<th>' )
        out.write( 'Entry' )
        out.write( '</th>' )
        out.write( new_line( ) )
        out.write( '<th>' )
        out.write( 'Action' )
        out.write( '</th>' )
        out.write( new_line( ) )
        out.write( '</tr>' )
        out.write( new_line( ) )
        for m7 in webdsl.querylist.AllDbQuerySet(data.Entry).order_by( 'date' ) :
            out.write( '<tr>' )
            out.write( '<td>' )
            out.write( cgi.escape( m7.sender.username ,  True ) )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '<td>' )
            out.write( webdsl.parse_text( m7.message ) )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '<td>' )
            out.write( '<a href="' + '/editEntry' + '/' + str( m7.key().id( ) ) + '' + '">' )
            out.write( 'Edit' )
            out.write( new_line( ) )
            out.write( '</a>' )
            out.write( '</td>' )
            out.write( new_line( ) )
            out.write( '</tr>' )
            out.write( new_line( ) )
        out.write( new_line( ) )
        out.write( '</table>' )
        self.scope [ 'newEntry2' ] = data.Entry( )
        out.write( '<div class="' + 'section1' + '">' )
        out.write( '<div class="' + 'section2' + '">' )
        out.write( '<' + 'h2' + '>' )
        out.write( 'Add entry' )
        out.write( new_line( ) )
        out.write( '</' + 'h2' + '>' )
        out.write( new_line( ) )
        self.template_bindings['addEntryTemplate'](self, self.rh, out, m10=self.scope['newEntry2']).render( )
        out.write( new_line( ) )
        out.write( '</div>' )
        out.write( new_line( ) )
        out.write( '</div>' )
class Body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( 'Nothin' )
class Foot(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<hr/>' )
        out.write( '<p>' )
        out.write( 'Powered by' )
        out.write( new_line( ) )
        out.write( '</p>' )
        out.write( '<img src="' + 'http://webdsl.org/webdslorg/images/WebDSL-small.png' + '"/>' )
class Head(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<' + 'h1' + '>' )
        out.write( 'Wiki Guestbook' )
        out.write( new_line( ) )
        out.write( '</' + 'h1' + '>' )
        out.write( '<a href="' + '/' + '' + '">' )
        out.write( 'Home' )
        out.write( new_line( ) )
        out.write( '</a>' )
        out.write( ' | ' )
        out.write( '<a href="' + '/register' + '' + '">' )
        out.write( 'Register' )
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
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = ProcedureStatus_body
class ProcedureStatus_body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<' + 'h1' + '>' )
        out.write( cgi.escape( self.scope['s1'].name ,  True ) )
        out.write( new_line( ) )
        out.write( '</' + 'h1' + '>' )
        out.write( '<table>' )
        out.write( '<tr>' )
        out.write( '<td>' )
        out.write( 'Name: ' )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '<td>' )
        out.write( cgi.escape( self.scope['s1'].name ,  True ) )
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
        out.write( cgi.escape( self.scope['s1'].enabled ,  True ) )
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
        out.write( cgi.escape( self.scope['s1'].caller.name ,  True ) )
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
        out.write( cgi.escape( self.scope['s1'].returnstate ,  True ) )
        out.write( '</td>' )
        out.write( new_line( ) )
        out.write( '</tr>' )
        out.write( new_line( ) )
        out.write( new_line( ) )
        out.write( '</table>' )
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
    def prepare_templates(self):
        self.template_bindings [ 'body' ] = AllTasks_body
class AllTasks_body(webdsl.utils.RequestHandler):
    def render(self):
        out = self.out
        out.write( '<' + 'h1' + '>' )
        out.write( 'All tasks' )
        out.write( new_line( ) )
        out.write( '</' + 'h1' + '>' )
        out.write( '<div class="' + 'section1' + '">' )
        out.write( '</div>' )
webdsl.utils.register( '/user/([^/]+)' ,  User ,  [ ( 'u1' , '' ,  unicode ,  data.User ) ] )
webdsl.utils.register( '/editAll' ,  EditAll ,  [ ] )
webdsl.utils.register( '/editEntry/([^/]+)' ,  EditEntry ,  [ ( 'm8' , '' ,  long ,  data.Entry ) ] )
webdsl.utils.register( '/register' ,  Register ,  [ ] )
webdsl.utils.register( '/' ,  Home ,  [ ] )
webdsl.utils.register( '/procedureStatus/([^/]+)' ,  ProcedureStatus ,  [ ( 's1' , '' ,  long ,  data.ProcedureStatus ) ] )
webdsl.utils.register( '/allTasks' ,  AllTasks ,  [ ] )