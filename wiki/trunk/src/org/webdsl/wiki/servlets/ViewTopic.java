package org.webdsl.wiki.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import org.webdsl.wiki.domain.*;
import org.webdsl.wiki.utilities.*;
import org.webdsl.wiki.servlets.*;

public class ViewTopic extends HttpServlet 
{

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	HttpSession session = request.getSession();
	String path = request.getPathInfo() == null ? "" : request.getPathInfo();

	Session hsession = HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = hsession.beginTransaction();

	Topic root = RootTopic.getRootTopic(hsession);

	try{ 
	  TopicPath topicpath = new TopicPath(root, path);
	  Topic topic = (Topic)topicpath.get(topicpath.size() - 1);

          request.setAttribute("path", path);
	  request.setAttribute("pathelements", topicpath.getElements());
	  request.setAttribute("topic", topic);

	  String mimetype = topic.getMimetype();

	  //if ("text/css".equals(mimetype)) {
		// copy text field to output
		// set result type
  	  //} else if ("text/wiki".equals(mimetype)) {
		// call wiki parser	  
  	  //} else if ("text/plain".equals(mimetype)) {
	    request.setAttribute("subtopics", topic.getSubtopics().keySet());
	    request.setAttribute("authors", topic.getAuthorNames());
	    RequestDispatcher dispatcher =
	      request.getRequestDispatcher("/WEB-INF/classes/view/topics/TopicView.jsp");
          //}
	  dispatcher.forward(request, response);
          transaction.commit();
	  hsession.close();
        } catch (TopicDoesNotExistException e) {
	  transaction.rollback();
	  hsession.close();
	  response.sendRedirect("/wiki1/edit/" + path);
        }

	//String topicweb = topic.getWebName();

	//request.setAttribute("SCRIPTURL",      request.getContextPath());

	// top-level including page
	//request.setAttribute("BASETOPIC",      topicname);
	//request.setAttribute("BASEWEB",        topicweb);
	//request.setAttribute("BASEPARENTWEB",  topic.getParentWeb());

	// page where current page is included in
	//request.setAttribute("INCLUDINGTOPIC", topicname);
	//request.setAttribute("INCLUDINGWEB",   topicweb);

	// referring to the page we're currently rendering
	//request.setAttribute("TOPIC",          topicname);
	//request.setAttribute("WEB",            topicweb);
	//request.setAttribute("SPACEDTOPIC",    topicname);

	//request.setAttribute("HOMETOPIC", topicweb + "WebHome");
	//request.setAttribute("MAINWEB", "/");

	//HttpSession session = request.getSession();
	//request.setAttribute("USERNAME", session.getAttribute("USERNAME"));
      	
	// actual page is included from skin page

	//String skin = request.getParameter("skin");
	//if (skin == null)
	//    {
	//	skin = (String)session.getAttribute("SKIN");
	//	if (skin == null)
	//	    {
	//		skin = "/Skins/FlexibleSkin";
	//	    }
	//    }

	//Topic skintopic = new Topic();
	//skintopic.setTopicname(skin);
	//skintopic.getFromDatabase();
	//response.setContentType("text/html");

	//HashSet includemap = new HashSet();
	//includemap.add(skin);
	//request.setAttribute("includemap", includemap);

	//skintopic.renderTopicText(request, response);
    }

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doPost(request, response);
    }

}
