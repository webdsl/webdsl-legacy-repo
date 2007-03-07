<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

  <jsp:useBean id="userinfo"
    type="org.webdsl.wiki.domain.User"
    scope="request" />
    
    <head><title>Login</title></head>
  
    <body bgcolor="white">
      <h1>Login</h1>
      
    <form method="POST">

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
	  <%= userinfo.hasPassword() ? "Password*" : "<b>Password*</b>" %>
	</td>
	<td>
	  <input type="password" name="password" value="<jsp:getProperty name="userinfo" property="password" />" /><br />
	</td>
      </tr>
      
    </table>
    
    Fields marked * are required.

    <br />

    <input type="submit" value="Login"/>

  </form>
    
    <a href="/user1/">Main</a> 
    | <a href="/user1/login">Login</a> 
    | <a href="/user1/logout">Logout</a> 
    | <a href="/user1/users">Users</a> 
    | <a href="/user1/register-user">Register</a>

  </body>

</html>
