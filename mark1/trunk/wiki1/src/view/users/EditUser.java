package view.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.hibernate.*;

import users.*;
import view.users.*;
import view.html.*;
import util.HibernateUtil;
import util.BeanUtilities;
import util.ServletUtilities;

public class EditUser extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	String username = ServletUtilities.getNameFromPathInfo(request.getPathInfo());

	if(username == null) {
           response.sendRedirect("/wiki1");
           return;
	   // no username in pathinfo
	}

	User user = User.getByName(username);
       
	if (user != null && user.isComplete())
	    {	
		HttpSession session = request.getSession();
		session.setAttribute("userinfo", user);

		request.setAttribute("userinfo", user);
		request.setAttribute("next", "/wiki1/save-user");
		request.setAttribute("title", "Change Profile of " + user.getUsername());
		request.setAttribute("button", "Save");

		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/view/users/UserEntryForm.jsp");
		dispatcher.forward(request, response);
	    }
	else 
	    {
		response.sendError(response.SC_NOT_FOUND, "no such user");
	    }
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doGet(request, response);
    }
}
