<%@ page import="java.util.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <jsp:useBean id="topic"     type="org.webdsl.wiki.domain.Topic" scope="request" />
  <jsp:useBean id="text"      type="java.lang.String" scope="request" />
  <jsp:useBean id="viewer"    type="org.webdsl.wiki.viewers.Viewer" scope="request" />
  <jsp:useBean id="path"      type="java.lang.String" scope="request" />
  <jsp:useBean id="subtopics" type="java.util.Set"    scope="request" />
  <jsp:useBean id="authors"   type="java.util.Set"    scope="request" />
  <jsp:useBean id="pathelements"   type="java.util.List"    scope="request" />

  <%
    String title = topic.getTitle();
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
      <% viewer.writeView(request, topic, out); %>
      <hr>
      <a href="/wiki1/edit<%= path %>">Edit</a> | 
      <a href="/wiki1/export<%= path %>">Export</a> |
      <a href="/wiki1/import<%= path %>">Import</a>
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
