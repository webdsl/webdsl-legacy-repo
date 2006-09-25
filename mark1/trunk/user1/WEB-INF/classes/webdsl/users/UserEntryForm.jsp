<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

  <jsp:useBean id="userinfo"
    type="webdsl.users.UserInfo"
    scope="request" />
    
  <jsp:useBean id="next"
    type="java.lang.String"
    scope="request" />

  <jsp:useBean id="title"
    type="java.lang.String"
    scope="request" />

  <jsp:useBean id="button"
    type="java.lang.String"
    scope="request" />

    <head><title><%= title %></title></head>
  
    <body bgcolor="white">
      <h1><%= title %></h1>
      
    <form action="<%= next %>" method="POST">

    <table>
      <tr>
	<td> 
	  <%= userinfo.hasUsername() ? "Name*" : "<b>Name*</b>" %>
	</td>
	<td>
	  <input type="text" name="username" value="<jsp:getProperty name="userinfo" property="username" />" />
	</td>
      </tr>
      
      <tr>
	<td> 
	  <%= userinfo.hasFullname() ? "Full name*" : "<b>Full name*</b>" %>
	</td>
	<td>
	  <input type="text" name="fullname" value="<jsp:getProperty name="userinfo" property="fullname" />" /><br />
	</td>
      </tr>
      
      <tr>
	<td> 
	  <%= userinfo.hasEmail() ? "Email*" : "<b>Email*</b>" %>
	</td>
	<td>
      <input type="text" name="email" value="<jsp:getProperty name="userinfo" property="email" />" /><br />
	</td>
      </tr>
      
      <tr>
	<td> 
	  URL
	</td>
	<td>
	  <input type="text" name="url" value="<jsp:getProperty name="userinfo" property="url" />" /><br />
	</td>
      </tr>
      
      <tr>
	<td> 
	  <%= userinfo.hasPassword() ? "Password*" : "<b>Password*</b>" %>
	</td>
	<td>
	  <input type="password" name="password" value="<jsp:getProperty name="userinfo" property="password" />" /><br />
	</td>
      </tr>
      
      <tr>
	<td> 
	  <%= userinfo.hasPasswordcheck() ? "Password*" : "<b>Password*</b>" %>
	</td>
	<td>
	  <input type="password" name="passwordcheck" value="<jsp:getProperty name="userinfo" property="passwordcheck" />" /><br />
	</td>
      </tr>
    </table>
    
    Fields marked * are required.

    <br />

    <input type="submit" value="<%= button %>"/>

  </form>
    
    <a href="/user1/">Main</a> 
    | <a href="/user1/login">Login</a> 
    | <a href="/user1/logout">Logout</a> 
    | <a href="/user1/users">Users</a> 
    | <a href="/user1/register-user">Register</a>

  </body>

</html>
