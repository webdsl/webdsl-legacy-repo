package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

public class EditUser extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
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
		response.sendRedirect("/user1");
		// no username in pathinfo
	    }

	UserInfo userinfo = new UserInfo();
	userinfo.setUsername(username);
	getUserFromDB(userinfo);
       
	if (userinfo.isComplete())
	    {		
		request.setAttribute("userinfo", userinfo);
		request.setAttribute("next",  "/user1/save-user");
		request.setAttribute("title", 
				     "Change Profile of " + userinfo.getUsername());
		request.setAttribute("button", "Save");

		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/webdsl/users/UserEntryForm.jsp");
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

    UserInfo getUserFromDB(UserInfo userinfo)
    {
	String query = 
	    "SELECT username, name, email, url, password FROM user " 
	    + "WHERE username = '" + userinfo.getUsername() + "'";
	
	return (UserInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeUserInfoFromResultSet(userinfo));
    }
}
