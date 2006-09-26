<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:useBean id="title" type="java.lang.String" scope="request" />
  <jsp:useBean id="topics" type="java.util.ArrayList" scope="request" />

    <head>
      <title><%= title %></title>
    </head>

    <body>
      <h1><%= title %></h1>
      <hr>

      <ul>

      <% for(int i = 0; i < topics.size(); i++) 
         { String topicname = ((webdsl.topics.TopicInfo)topics.get(i)).getTopicname(); %>

         <li> <a href="/user1/view/<%= topicname %>"><%= topicname %></a> </li>

      <% } %>

      </ul>

      <hr>
    </body>
</html>
