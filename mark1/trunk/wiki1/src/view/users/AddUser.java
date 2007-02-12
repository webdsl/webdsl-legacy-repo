package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import users.*;
import view.users.*;
import view.html.*;
import util.HibernateUtil;
import util.BeanUtilities;

public class AddUser extends HttpServlet 
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
	User user = new User();
	BeanUtilities.populateBean((Object)user, request);

	HibernateUtil.getSessionFactory()
	             .getCurrentSession()
                     .save(user);

	response.sendRedirect("/user1/user/" + user.getUsername());

	// @TODO: validate user data

	/*
	if (user.isComplete())
	    {
		addUserToDB(user);
		response.sendRedirect("/user1/user/" + user.getUsername());
	    }
	else 
	    {
		if (!user.passwordIsConsistent())
		    {
			user.setPassword("");
			user.setPasswordcheck("");
		    }
		request.setAttribute("user", user);

		request.setAttribute("next",  "/user1/register-user");
		request.setAttribute("title", "Register New User");
		request.setAttribute("button", "Register");

		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/webdsl/users/UserEntryForm.jsp");
		dispatcher.forward(request, response);
	    }
         */
    }

}
