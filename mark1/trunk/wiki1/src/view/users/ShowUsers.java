package view.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import view.html.*;
import users.*;
import util.HibernateUtil;

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
	Iterator users = HibernateUtil.getSessionFactory()
				      .getCurrentSession()
                                      .createQuery("from User")
 				      .list()
				      .iterator();
	while(users.hasNext())
          {
	    User u = (User)users.next();
	    out.println("<li>"
			+ "<a href=/user1/user/" + u.getUsername() + ">"
			+ u.getFullname()
			+ "</a>"
			+ "</li>");
          }
    }

}
