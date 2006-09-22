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

	if (userinfo.isComplete())
	    {
		addUserToDB(userinfo);
		// forward to ShowUser servlet
		response.sendRedirect("/user1/user/" + userinfo.getUsername());
		
	    }
	else 
	    {
		if (!userinfo.passwordIsConsistent()) 
		    {
			userinfo.setPassword("");
			userinfo.setPasswordcheck("");
		    }
		showEntryForm(request, response, userinfo);
	    }
    }

    public static void showEntryForm(HttpServletRequest request,
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

    private static String inputElement(String prompt,
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

}
