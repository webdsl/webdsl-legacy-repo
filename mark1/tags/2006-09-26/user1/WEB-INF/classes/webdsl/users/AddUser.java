package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

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
	UserInfo userinfo = new UserInfo();
	BeanUtilities.populateBean((Object)userinfo, request);

	if (userinfo.isComplete())
	    {
		addUserToDB(userinfo);
		response.sendRedirect("/user1/user/" + userinfo.getUsername());
	    }
	else 
	    {
		if (!userinfo.passwordIsConsistent())
		    {
			userinfo.setPassword("");
			userinfo.setPasswordcheck("");
		    }
		request.setAttribute("userinfo", userinfo);

		request.setAttribute("next",  "/user1/register-user");
		request.setAttribute("title", "Register New User");
		request.setAttribute("button", "Register");

		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/webdsl/users/UserEntryForm.jsp");
		dispatcher.forward(request, response);
	    }
    }

    private String addUserToDB(UserInfo userinfo)
    {
	String query = 
	    "INSERT INTO user (username, password, name, email, url) VALUES"
	    + "('"   + userinfo.getUsername()
	    + "', '" + userinfo.getPassword()
	    + "', '" + userinfo.getFullname()
	    + "', '" + userinfo.getEmail()
	    + "', '" + userinfo.getUrl()
	    + "');";
	
	return DataBaseUtilities.updateDataBase(query);
    }
}
