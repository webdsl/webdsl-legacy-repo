package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;

public class RemoveUser extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	String username = request.getPathInfo();
	if(username != null && username.startsWith("/"))
	    {
		username = username.substring(1);
		deleteUser(request, response, username);
	    }
	else
	    response.sendError(response.SC_BAD_REQUEST,
			       "no username in pathinfo");
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	UserInfo userinfo = new UserInfo();
	BeanUtilities.populateBean((Object)userinfo, request);

	if (userinfo.hasUsername())
	    deleteUser(request, response, userinfo.getUsername());
	else
	    response.sendError(response.SC_BAD_REQUEST,
			       "no username provided");
    }

    public void deleteUser(HttpServletRequest request,
			   HttpServletResponse response,
			   String username)
	throws ServletException, IOException 
    {
	UserInfo userinfo = new UserInfo();
	userinfo.setUsername(username);
	getUserFromDB(userinfo);
       
	if (userinfo.isComplete())
	    {
		deleteUserFromDB(userinfo);
		response.sendRedirect("/user1/users");
	    }
	else 
	    response.sendError(response.SC_NOT_FOUND, 
			       "no such user " + username);
    }

    private UserInfo getUserFromDB(UserInfo userinfo)
    {
	String query = 
	    "SELECT username, name, email, url, password FROM user " 
	    + "WHERE username = '" + userinfo.getUsername() + "'";
	
	return (UserInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeUserInfoFromResultSet(userinfo));
    }

    private String deleteUserFromDB(UserInfo userinfo)
    {
	String query = 
	    "delete from user "
	    + "where username = '" + userinfo.getUsername() + "'; ";
	
	return DataBaseUtilities.updateDataBase(query) + "";
    }

}
