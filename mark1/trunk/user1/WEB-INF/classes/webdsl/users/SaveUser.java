package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

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
	UserInfo userinfo = new UserInfo();
	BeanUtilities.populateBean((Object)userinfo, request);

	if (userinfo.isComplete())
	    {
		String result = changeUserInDB(userinfo);
		response.sendRedirect("/user1/user/" + userinfo.getUsername());
	    }
	else 
	    {
		request.setAttribute("userinfo", userinfo);
		request.setAttribute("next", "/user1/save-user");
		request.setAttribute("title", "Change Profile of " + userinfo.getUsername());
		request.setAttribute("button", "Save");
		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/webdsl/users/UserEntryForm.jsp");
		dispatcher.forward(request, response);
	    }
    }

    private String changeUserInDB(UserInfo userinfo)
    {
	String query = 
	    "update user set "
	    + "password = '" + userinfo.getPassword() + "', "
	    + "name     = '" + userinfo.getFullname() + "', "
	    + "email    = '" + userinfo.getEmail()    + "', "
	    + "url      = '" + userinfo.getUrl()      + "'"
	    + "where username = '" + userinfo.getUsername() + "';";
	
	return DataBaseUtilities.updateDataBase(query) + "";
    }
}
