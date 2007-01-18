package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

public class ChangeUser extends HttpServlet 
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
		//AddUser.showEntryForm(request, response, userinfo);
		
		request.setAttribute("userinfo", userinfo);
		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/webdsl/users/UserEntryForm.jsp");
		dispatcher.forward(request, response);
	    }
	else 
	    {
		response.sendRedirect("/user1/user/users");
		//response.sendRedirect("/user1");
		// no such user registered
	    }
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	UserInfo userinfo = new UserInfo();
	BeanUtilities.populateBean((Object)userinfo, request);

	if (userinfo.isComplete())
	    {
		changeUserInDB(userinfo);
		response.sendRedirect("/user1/user/" + userinfo.getUsername());
	    }
	else 
	    {
		request.setAttribute("userinfo", userinfo);
		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/webdsl/users/UserEntryForm.jsp");
		dispatcher.forward(request, response);
	    }
    }

    private String changeUserInDB(UserInfo userinfo)
    {
	String query = 
	    "update user set "
	    + "username = '" + userinfo.getUsername() + "', "
	    + "password = '" + userinfo.getPassword() + "', "
	    + "name     = '" + userinfo.getFullname() + "', "
	    + "email    = '" + userinfo.getEmail()    + "', "
	    + "url      = '" + userinfo.getUrl()      + "'; ";
	
	return DataBaseUtilities.updateDataBase(query) + "";
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
