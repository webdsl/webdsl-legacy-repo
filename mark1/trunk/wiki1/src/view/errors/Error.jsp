<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

  <jsp:useBean id="script" type="java.lang.String" scope="request" />
  <jsp:useBean id="error" type="java.lang.String" scope="request" />

  <head>
    <title>
      Error: <%= script %>
    </title>
  </head>
      
  <body bgcolor="white">
    <h1>Error: <%= script %></h1>

    <%= error %>

  </body>

</html>
