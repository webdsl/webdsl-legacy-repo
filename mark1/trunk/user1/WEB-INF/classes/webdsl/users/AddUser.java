package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;

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

	String username = request.getPathInfo();
	if(username != null && username.startsWith("/"))
	    {
		username = username.substring(1);
	    }
	else
	    username = "";

	if (!username.equals("") && !userinfo.hasUsername(username))
	    {
		userinfo.makeEmpty();
		userinfo.setUsername(username);
		// check that username is an alphanumeric string
	    }

	UserInfo db_userinfo = new UserInfo();
	db_userinfo.setUsername(userinfo.getUsername());
	getUserFromDB(db_userinfo);
	db_userinfo.setChanged(false);
	boolean user_existed = (db_userinfo.getUsername() != null);
	db_userinfo.partiallyUpdateFrom(userinfo);
       
	if (db_userinfo.isComplete() && !userinfo.getChange())
	    {
		if (db_userinfo.isChanged()) 
		    if (user_existed)
			changeUserInDB(db_userinfo);
		    else 
			addUserToDB(db_userinfo);
		showUserInfo(request, response, db_userinfo);
	    }
	else
	    {
		db_userinfo.setPassword("");
		db_userinfo.setPasswordcheck("");
		showEntryForm(request, response, db_userinfo);
	    }
    }

    private void showUserInfo(HttpServletRequest request,
			       HttpServletResponse response, 
			       UserInfo userinfo)
	throws ServletException, IOException 
    {
	PrintWriter out = response.getWriter();

	response.setContentType("text/html");

	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	    "Transitional//EN\">\n";

	out.println(docType 
		    + "<HTML>\n" 
		    + "<HEAD><TITLE>AddUser</TITLE></HEAD>\n" 
		    + "<BODY BGCOLOR=\"#FDF5E6\">\n" 
		    + "<H1>AddUser</H1>\n");

	    
	out.println("<ul>"
		    + "<li> username: " + userinfo.getUsername() + "</li>"
		    + "<li> name: "      + userinfo.getFullname() + "</li>"
		    + "<li> email: "     + userinfo.getEmail() + "</li>"
		    + "<li> url: "       + userinfo.getUrl() + "</li>"
		    + "</ul>");

	    
	out.println("<form method=\"POST\">"
		    + "<input type=\"checkbox\" name=\"change\" checked />"
		    + "<input type=\"submit\" value=\"change user info\" />"
		    + "</form>");

	out.println("</BODY></HTML>");

    }

    private void showEntryForm(HttpServletRequest request,
			       HttpServletResponse response, 
			       UserInfo userinfo)
	throws ServletException, IOException 
    {
	boolean isPartlyComplete = userinfo.isPartlyComplete();

	response.setContentType("text/html");

	PrintWriter out = response.getWriter();

	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	    "Transitional//EN\">\n";

	out.println
	    (docType 
	     + "<HTML>\n" 
	     + "<HEAD><TITLE>AddUser</TITLE></HEAD>\n" 
	     + "<BODY BGCOLOR=\"#FDF5E6\">\n" 
	     + "<H1>AddUser</H1>\n"
	     + "<form method=\"POST\">"
	     + inputElement("Name",      "text",     "username",      userinfo.getUsername(),      true)
	     + inputElement("Full name", "text",     "fullname",      userinfo.getFullname(),      true)
	     + inputElement("Email",     "text",     "email",         userinfo.getEmail(),         true)
	     + inputElement("URL",       "text",     "url",           userinfo.getUrl(),           false)
	     + inputElement("Password",  "password", "password",      userinfo.getPassword(),      true)
	     + inputElement("Password",  "password", "passwordcheck", userinfo.getPasswordcheck(), true)
	     + "<input type=\"submit\" />"
	     + "</BODY></HTML>"
	     );
    }

    private String inputElement(String prompt,
				String type,
				String name,
				String value,
				boolean shouldPrompt)
    {
	String message = "";
	if (shouldPrompt)
	    if ((value == null) || value.trim().equals(""))
		message = "<b>" + prompt + "*</b>";
	    else 
		message = prompt + "*";
	else
	    message = prompt;
	
	if (value == null)
	    value = "";

	return
	    (message
	     + ": "
	     + "<input type=\"" + type + "\" name=\"" + name + "\"" 
	     + " value=\"" + value + "\" /><br />\n"
	     );
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

    private String changeUserInDB(UserInfo userinfo)
    {
	String query = 
	    "update user set "
	    + "username = '" + userinfo.getUsername() + "', "
	    + "password = '" + userinfo.getPassword() + "', "
	    + "name     = '" + userinfo.getFullname() + "', "
	    + "email    = '" + userinfo.getEmail()    + "', "
	    + "url      = '" + userinfo.getUrl()      + "'; ";
	
	return DataBaseUtilities.updateDataBase(query);
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
