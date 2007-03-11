<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:useBean id="topic"     type="org.webdsl.wiki.domain.Topic"     scope="request" />
  <jsp:useBean id="path"      type="java.lang.String" scope="request" />
  <jsp:useBean id="subtopics" type="java.util.Set"    scope="request" />
  <jsp:useBean id="authors"   type="java.util.Set"    scope="request" />
  <jsp:useBean id="pathelements"   type="java.util.List"    scope="request" />

  <%
    String title = topic.getTitle();
    String text = topic.getText();
    if (text == null)
      text = "";
    else
      text = org.webdsl.wiki.utilities.EscapeChars.escape(text);
    String mimetype = topic.getMimetype();
  %>

    <head>
      <title><%= title %> (Edit)</title>
    </head>

    <body>
      <h1><%= title %> (Edit) </h1>
      <hr>
        <a href="/wiki1/view">Root</a>
      <% String prefix = "";
         for(Object elem : pathelements) 
         { 
              prefix = prefix + "/" + (String)elem; %>
           | <a href="/wiki1/view<%= prefix %>"><%= (String)elem %></a>
      <% } %>
      <hr>

      <form action="/wiki1/save<%= path %>" method="POST">
	
	title: <input type="text" name="title" value="<%= title %>" />

	<br />

	<textarea name="text" wrap="virtual" rows="15" cols="75"><%= text %></textarea>

	<br />

	type: <input type="text" name="mimetype" value="<%= mimetype %>" />

      <hr>
	<input type="submit" value="Save" />

      </form>
     
      <p />
      Subtopics:
      <ul>
      <% for(Object name : subtopics)
           { %>
             <li>
               <a href="/wiki1/view<%= path %>/<%= (String)name %>"><%= (String)name%></a>
             </li>
      <%   } %>
      </ul>

      <p />
      Authors:
      <ul>
      <% for(Object name : authors)
           { %>
             <li> <a href="/wiki1/user/<%= (String)name %>"><%= (String)name%></a></li>
      <%   } %>
      </ul>

    </body>
</html>
