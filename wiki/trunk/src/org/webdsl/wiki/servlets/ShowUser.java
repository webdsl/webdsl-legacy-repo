package view.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import users.*;
import view.users.*;
import view.html.*;
import util.HibernateUtil;
import util.ServletUtilities;

public class ShowUser extends HttpServlet 
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
	String username = ServletUtilities.getNameFromPathInfo(request.getPathInfo());

	if(username == null) {
           response.sendRedirect("/wiki1");
	}

	User user = User.getByName(username);

	if (user == null) {
           response.sendRedirect("/wiki1/register-user");
	} else {
	  request.setAttribute("userinfo", user);
	  RequestDispatcher dispatcher =
	      request.getRequestDispatcher(
                "/WEB-INF/classes/view/users/UserView.jsp"
              );
	  dispatcher.forward(request, response);
  	}
    }

}
