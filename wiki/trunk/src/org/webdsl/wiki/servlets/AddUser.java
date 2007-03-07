package org.webdsl.wiki.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.webdsl.wiki.domain.*;
import org.webdsl.wiki.utilities.*;

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

	if (user.isComplete()) {
	  try {
	    user.save();
            response.sendRedirect("/wiki1/user/" + user.getUsername());
	    return;
          } catch (Exception e) { 
              /* duplicate entry */ 
              user.setUsername(user.getUsername() + " already in use");
          }
	}

	//if (!user.passwordIsConsistent())
	//    {
	//	user.setPassword("");
	//	user.setPasswordcheck("");
	//    }

	request.setAttribute("userinfo", user);
	request.setAttribute("next",  "/wiki1/register-user");
	request.setAttribute("title", "Register New User");
	request.setAttribute("button", "Register");

	RequestDispatcher dispatcher =
	  request.getRequestDispatcher("/WEB-INF/classes/view/users/UserEntryForm.jsp");
	dispatcher.forward(request, response);
    }

}
