<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

  <jsp:useBean id="user" type="users.User" scope="session" />

  <jsp:useBean id="continuation" type="String" scope="session" />
    
    <head><title>Login</title></head>
  
    <body bgcolor="white">
      <h1>Login</h1>
      
    <form method="POST">

    <table>
      <tr>
	<td> 
	  Username :
	  <%--= user.hasUsername() ? "Name*" : "<b>Name*</b>" --%>
	</td>
	<td>
	  <input type="text" name="username" value="" />
	</td>
      </tr>
      
      <tr>
	<td> 
	  Password:
	</td>
	<td>
	  <input type="password" name="password" value="" /><br />
	</td>
      </tr>
      
    </table>
    
    Fields marked * are required.

    <br />

    <input type="submit" value="Login"/>

  </form>
    
    <a href="/wiki1/">Main</a> 
    | <a href="/wiki1/login">Login</a> 
    | <a href="/wiki1/logout">Logout</a> 
    | <a href="/wiki1/users">Users</a> 
    | <a href="/wiki1/register-user">Register</a>
    | <jsp:include page="/WEB-INF/classes/view/users/LoggedInAs.jsp" />
    | next: <%= continuation %>

  </body>

</html>
