package org.webdsl.wiki.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import org.webdsl.wiki.domain.*;
import org.webdsl.wiki.utilities.*;
import org.webdsl.wiki.servlets.*;


public class SaveTopic extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	HttpSession session = request.getSession();
	User user = (User)session.getAttribute("user");
	String path = request.getPathInfo() == null ? "" : request.getPathInfo();

	if (user == null) 
        {
	  session.setAttribute("continuation", "/wiki1/edit" + path);
	  response.sendRedirect("/wiki1/login");
    	} 
        else 
        {
	  Session hsession = HibernateUtil.getSessionFactory().openSession();
	  Transaction transaction = hsession.beginTransaction();
	
	  log("adding user from session: " 
	      + user.getId() + " " + user.getUsername()
	      + "\n authors from topic:\n");

	  user = (User)hsession.merge(user);

	  log("adding root topic");

	  Topic root = RootTopic.getRootTopic(hsession);
	  TopicPath topicpath = new TopicPath(root, path, user);
	  Topic topic = (Topic)topicpath.get(topicpath.size() - 1);

	  if (topic == null)
	  {
	     response.sendRedirect("/wiki1/");
	     return;
	  } 
          else
          {
	     for(Object a : topic.getAuthors())
   	       {
		  User author = (User)a;
	          log(author.getId() + " : " 
					+ author.getUsername()
					+ " : " 
					+ author.equals(user));
	       }

	     hsession.merge(topic);

	     BeanUtilities.populateBean((Object)topic, request);
	
	     log("adding author");

	     topic.addAuthor(user);

	     log("done");

             transaction.commit();
	     hsession.close();
	     response.sendRedirect("/wiki1/view/" + path);
          }
        }
    }

}
