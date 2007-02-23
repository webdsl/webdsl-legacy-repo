package view.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import users.*;
import topics.*;
import xml.*;
import view.users.*;
import view.html.*;
import util.HibernateUtil;
import util.ServletUtilities;
import util.BeanUtilities;

public class ExportTopics extends HttpServlet 
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
	  session.setAttribute("continuation", "/wiki1/export" + path);
	  response.sendRedirect("/wiki1/login");
    	} 
        else 
        {
	  Session hsession = HibernateUtil.getSessionFactory().openSession();
	  Transaction transaction = hsession.beginTransaction();
	
	  user = (User)hsession.merge(user);

	  log("adding root topic");

	  Topic root = RootTopic.getRootTopic(hsession);
	  TopicPath topicpath = new TopicPath(root, path, user);
	  Topic topic = (Topic)topicpath.get(topicpath.size() - 1);

	  if (topic == null)
	  {
	     response.sendRedirect("/wiki1/");
	     hsession.close();
	     return;
	  } 
          else
          {
	     response.setContentType("text/xml");
	     ToXML toxml = new ToXML(response.getWriter());
	     toxml.export(topic);
             transaction.commit();
	     hsession.close();
          }
        }
    }

}
