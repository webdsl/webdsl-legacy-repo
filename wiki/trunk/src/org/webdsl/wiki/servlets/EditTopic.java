package org.webdsl.wiki.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import org.webdsl.wiki.domain.*;
import org.webdsl.wiki.utilities.*;
import org.webdsl.wiki.servlets.*;

public class EditTopic extends HttpServlet 
{
    public void doGet(HttpServletRequest request,
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

	  hsession.load(user, user.getId());

	  Topic root = RootTopic.getRootTopic(hsession);
	  TopicPath topicpath = new TopicPath(root, path, user);
	  Topic topic = (Topic)topicpath.get(topicpath.size() - 1);

          request.setAttribute("path", path);
	  request.setAttribute("pathelements", topicpath.getElements());
	  request.setAttribute("topic", topic);
	  request.setAttribute("subtopics", topic.getSubtopics().keySet());
	  request.setAttribute("authors", topic.getAuthorNames());

          transaction.commit();
	  hsession.close();

	  RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/WEB-INF/classes/org/webdsl/wiki/servlets/EditTopicForm.jsp");
	  dispatcher.forward(request, response);
	}

    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doGet(request, response);
    }

}
