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

public class SaveUser extends HttpServlet 
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
	
	User user = (User)session.getAttribute("userinfo");

	if (user == null)
	  {
	     response.sendRedirect("/wiki1/");
	     return;
	  } 

	BeanUtilities.populateBean((Object)user, request);

	if (user.isComplete())
	    {
		user.update();
		response.sendRedirect("/wiki1/user/" + user.getUsername());
	    }
	else 
	    {
		request.setAttribute("user", user);
		request.setAttribute("next", "/wiki1/save-user");
		request.setAttribute("title", "Change Profile of " + user.getUsername());
		request.setAttribute("button", "Save");
		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/view/users/UserEntryForm.jsp");
		dispatcher.forward(request, response);
	    }
    }

}
