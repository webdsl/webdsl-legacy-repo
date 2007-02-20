<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:useBean id="topic"     type="topics.Topic"     scope="request" />
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
      text = view.html.EscapeChars.escape(text);
    String mimetype = topic.getMimetype();
  %>

    <head>
      <title><%= title %></title>
    </head>

    <body>
      <h1><%= title %></h1>
      <hr>
        <a href="/wiki1/view">Root</a>
      <% String prefix = "";
         for(Object elem : pathelements) 
         { 
              prefix = prefix + "/" + (String)elem; %>
           | <a href="/wiki1/view<%= prefix %>"><%= (String)elem %></a>
      <% } %>
      <hr>
      <pre><%= text %></pre>
      <hr>
      <a href="/wiki1/edit/<%= path %>">Edit</a>
      <hr>
      Type: <%= mimetype %>
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
    <hr>
    </body>
</html>
