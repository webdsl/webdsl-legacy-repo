<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:useBean id="path" type="java.lang.String" scope="request" />

    <head>
      <title><%= path %> (Import)</title>
    </head>

    <body>
      <h1><%= path %> (Import) </h1>

      <form action="/wiki1/import<%= path %>" 
            method="POST"
            enctype="multipart/form-data">
	
	topicname: <input type="text" name="topicname" />

	<br />
	
	<input type="file" name="topicfile" />

      <hr>
	<input type="submit" value="Import" />

      </form>

    </body>
</html>
