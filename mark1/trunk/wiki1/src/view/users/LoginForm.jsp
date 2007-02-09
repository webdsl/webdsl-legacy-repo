<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

  <jsp:useBean id="user"
    type="webdsl.users.UserInfo"
    scope="session" />
    
    <head><title>Login</title></head>
  
    <body bgcolor="white">
      <h1>Login</h1>
      
    <form method="POST">

    <table>
      <tr>
	<td> 
	  <%= user.hasUsername() ? "Name*" : "<b>Name*</b>" %>
	</td>
	<td>
	  <input type="text" name="username" 
		  value="<jsp:getProperty name="user" property="username" />" />
	</td>
      </tr>
      
      <tr>
	<td> 
	  <%= user.hasPassword() ? "Password*" : "<b>Password*</b>" %>
	</td>
	<td>
	  <input type="password" name="password" 
		  value="<jsp:getProperty name="user" property="password" />" /><br />
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
    | <jsp:include page="/WEB-INF/classes/webdsl/users/LoggedInAs.jsp" />

  </body>

</html>
