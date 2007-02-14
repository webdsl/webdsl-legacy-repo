<jsp:useBean id="user"
  type="users.User"
  scope="session" />
    
  <% if (user != null && user.getUsername() != null) { %>
  <jsp:getProperty name="user" property="fullname" />
  <% } %>


