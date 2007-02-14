package view.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.hibernate.*;

import users.*;
import topics.*;
import view.users.*;
import view.html.*;
import util.HibernateUtil;
import util.BeanUtilities;

public class CreateTopic extends HttpServlet 
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

	if (user == null) {
	  response.sendRedirect("/wiki1/login");
          return;
    	}

	String path = request.getPathInfo();

	Session hsession = HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = hsession.beginTransaction();
	hsession.update(RootTopic.getRootTopic());

	try {
	  TopicPath topicpath = new TopicPath(path, user);
          transaction.commit();
        } catch (Exception e) {
	  transaction.rollback();
        } finally {
	  hsession.close();	  
        }	

	response.sendRedirect("/wiki1/view/" + path);
    }

}

