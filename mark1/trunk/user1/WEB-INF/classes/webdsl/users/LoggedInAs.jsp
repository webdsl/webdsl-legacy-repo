<jsp:useBean id="user"
  type="webdsl.users.UserInfo"
  scope="session" />
    
  <% if (user.hasUsername()) { %>
  <jsp:getProperty name="user" property="fullname" />
  <% } %>


