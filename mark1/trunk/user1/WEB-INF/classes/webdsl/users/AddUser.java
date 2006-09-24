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

	HtmlUtilities.printHeader(out, "Register New User");

	out.println(
	     "<form method=\"POST\">"
	     + HtmlUtilities.inputElement("Name",      "text",     "username",      userinfo.getUsername(),      true)
	     + HtmlUtilities.inputElement("Full name", "text",     "fullname",      userinfo.getFullname(),      true)
	     + HtmlUtilities.inputElement("Email",     "text",     "email",         userinfo.getEmail(),         true)
	     + HtmlUtilities.inputElement("URL",       "text",     "url",           userinfo.getUrl(),           false)
	     + HtmlUtilities.inputElement("Password",  "password", "password",      userinfo.getPassword(),      true)
	     + HtmlUtilities.inputElement("Password",  "password", "passwordcheck", userinfo.getPasswordcheck(), true)
	     + "<input type=\"submit\" /> <p />"
	     );

	HtmlUtilities.printActions(out);
	HtmlUtilities.printFooter(out);
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
