<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:useBean id="topicinfo" type="webdsl.topics.TopicInfo" scope="request" />

  <%
    String topicname = topicinfo.getTopicname();
    String topictext = webdsl.html.EscapeChars.escape(topicinfo.getTopictext());
  %>

    <head>
      <title><%= topicname %> (Edit)</title>
    </head>

    <body>
      <h1><%= topicname %> (Edit)</h1>
      <hr>

      <form action="/user1/save" method="POST">
	
	<input type="hidden" name="topicname" value="<%= topicname %>" />

	<textarea name="topictext" name="text" wrap="virtual" rows="35" cols="100"><%= topictext %></textarea>

      <hr>
	<input type="submit" value="Save" />

      </form>

    </body>
</html>
