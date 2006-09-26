<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:useBean id="topicinfo" type="webdsl.topics.TopicInfo" scope="request" />

  <%
    String topicname = topicinfo.getTopicname();
    String topictext = topicinfo.getTopictext();
  %>

    <head>
      <title><%= topicname %></title>
    </head>

    <body>
      <h1><%= topicname %></h1>
      <hr>

      <%= topictext %>

      <hr>
      <a href="/user1/edit/<%= topicname %>">Edit</a>
    </body>
</html>
