package view.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import users.*;
import topics.*;
import view.users.*;
import view.html.*;
import util.HibernateUtil;
import util.ServletUtilities;
import util.BeanUtilities;

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
	  hsession.update(user);
	  hsession.update(RootTopic.getRootTopic());

	  TopicPath topicpath = new TopicPath(path, user);
	  Topic topic = (Topic)topicpath.get(topicpath.size() - 1);

	  if (topic == null)
	  {
	     response.sendRedirect("/wiki1/");
	     return;
	  } 
          else
          {
	     BeanUtilities.populateBean((Object)topic, request);
	     topic.addAuthor(user);
	     hsession.update(topic);
             transaction.commit();
	     hsession.close();
	     response.sendRedirect("/wiki1/edit/" + path);
          }
        }
    }

}
