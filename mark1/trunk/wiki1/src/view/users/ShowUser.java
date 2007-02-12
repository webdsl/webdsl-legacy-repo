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
	String username = request.getPathInfo();
	if(username != null && username.startsWith("/"))
	    {
		username = username.substring(1);
	    }
	else
	    {
		response.sendRedirect("/wiki1");
		// no username in pathinfo
	    }

	User user = User.getByName(username);

	request.setAttribute("userinfo", user);
	RequestDispatcher dispatcher =
	    request.getRequestDispatcher(
              "/WEB-INF/classes/view/users/UserView.jsp"
            );
	dispatcher.forward(request, response);
    }

}
