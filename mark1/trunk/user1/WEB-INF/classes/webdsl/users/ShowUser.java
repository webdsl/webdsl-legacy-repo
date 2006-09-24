package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

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
	    username = "";

	UserInfo userinfo = new UserInfo();
	getUserFromDB(username, userinfo);
       
	if (userinfo.isComplete())
	    {
		showUserInfo(request, response, userinfo);
	    }
	else 
	    {
		showUserInfo(request, response, userinfo);
		// note: something is wrong here; no user in database?
	    }
    }

    private void showUserInfo(HttpServletRequest request,
			       HttpServletResponse response, 
			       UserInfo userinfo)
	throws ServletException, IOException 
    {
	PrintWriter out = response.getWriter();

	response.setContentType("text/html");
	
	HtmlUtilities.printHeader(out, userinfo.getUsername());

	out.println("<ul>"
		    + "<li> username: " + userinfo.getUsername() + "</li>"
		    + "<li> name: "      + userinfo.getFullname() + "</li>"
		    + "<li> email: "     + userinfo.getEmail() + "</li>"
		    + "<li> url: "       + userinfo.getUrl() + "</li>"
		    + "</ul>");

	out.println("<a href=\"/user1/update-user/"
		    + userinfo.getUsername()
		    + "\">"
		    + "Change user info</a>");

	out.println(" | <a href=\"/user1/remove-user/"
		    + userinfo.getUsername()
		    + "\">"
		    + "Remove user</a> | ");

	HtmlUtilities.printActions(out);

	HtmlUtilities.printFooter(out);
    }

    UserInfo getUserFromDB(String username, UserInfo userinfo)
    {
	String query = 
	    "SELECT username, name, email, url, password FROM user " 
	    + "WHERE username = '" + username + "'";
	
	return (UserInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeUserInfoFromResultSet(userinfo));
    }
}

class MakeUserInfoFromResultSet implements ProcessResultSet
{
    UserInfo userinfo;

    public MakeUserInfoFromResultSet(UserInfo userinfo)
    {
	this.userinfo = userinfo;
    }

    public Object process(ResultSet rs)
    {
	try{
	    if(rs.next())
		{
		    userinfo.setUsername(rs.getString(1));
		    userinfo.setFullname(rs.getString(2));
		    userinfo.setEmail(rs.getString(3));
		    userinfo.setUrl(rs.getString(4));
		    userinfo.setPassword(rs.getString(5));
		    userinfo.setPasswordcheck(rs.getString(5));
		}
	} catch(Exception e) {}
	return (Object) userinfo;
    }
}
