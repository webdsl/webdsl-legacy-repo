<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

  <jsp:useBean id="userinfo" type="webdsl.users.UserInfo" scope="request" />

  <%
     String username = userinfo.getUsername();
     String fullname = userinfo.getFullname();
     String email    = userinfo.getEmail();
  %>

  <head>
    <title>
      <%= username %>
    </title>
  </head>
      
  <body bgcolor="white">
    <h1><%= username %></h1>

    <table>
      <tr> <td> Username:  </td> <td> <%= username %>                                      </td> </tr>
      <tr> <td> Full name: </td> <td> <%= fullname %> </td> </tr>

      <tr> <td> Email:     </td> <td> <a href="mailto:<%= email %>"><%= email %></a>    </td> </tr>

      <% if (userinfo.hasUrl()) { %>
      <tr> <td> URL:       </td> 
           <td> <a href="<%= userinfo.getUrl() %>">
	                 <%= userinfo.getUrl() %>                         
                </a> 
           </td> </tr>
      <% } %>

    </table>

   <a href="/user1/update-user/<%= username %>">Change user info</a>
   | <a href="/user1/remove-user/<%= username %>">Remove user</a> 
   | <a href="/user1/">Main</a> 
   | <a href="/user1/login">Login</a> 
   | <a href="/user1/logout">Logout</a> 
   | <a href="/user1/users">Users</a> 
   | <a href="/user1/register-user">Register</a>

  </body>

</html>
