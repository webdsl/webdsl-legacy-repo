package view.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import view.html.*;
import users.*;
import util.HibernateUtil;
import org.hibernate.Session;

public class ShowUsers extends HttpServlet 
{

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException 
    {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	HtmlUtilities.printHeader(out, "Registered Users");
	out.println("<ul>");
	printUsers(request, out);
	out.println("</ul>");
	HtmlUtilities.printActions(out);
	HtmlUtilities.printFooter(out);
	
    }

  private void printUsers(HttpServletRequest request, PrintWriter out) 
      throws ServletException, IOException 
    {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Iterator users = session.createQuery("from User")
 				.list()
				.iterator();
	while(users.hasNext())
          {
	    User u = (User)users.next();
	    out.println("<li>"
			+ "<a href=/wiki1/user/" + u.getUsername() + ">"
			+ u.getFullname()
			+ "</a>"
			+ "</li>");
          }
	session.getTransaction().commit();
    }

}
