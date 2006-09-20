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
	
	response.setContentType("text/html");
	
	PrintWriter out = response.getWriter();
	
	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	    "Transitional//EN\">\n";

	String users = addUserToDB(request);

	out.println(docType 
		    + "<HTML>\n" 
		    + "<HEAD><TITLE>AddUser</TITLE></HEAD>\n" 
		    + "<BODY BGCOLOR=\"#FDF5E6\">\n" 
		    + "<H1>AddUser</H1>\n" 
		    + users 
		    + "</BODY></HTML>");
    }

    private String addUserToDB(HttpServletRequest request)
    {
	UserInfo userinfo = new UserInfo();
	BeanUtilities.populateBean((Object)userinfo, request);

	String query = 
	    "INSERT INTO user (user_name, password, name, email, url) VALUES"
	    + "('"   + userinfo.getUsername()
	    + "', '" + userinfo.getPassword()
	    + "', '" + userinfo.getFullname()
	    + "', '" + userinfo.getEmail()
	    + "', '" + userinfo.getUrl()
	    + "');";
	
	return DataBaseUtilities.updateDataBase(query);
    }

}
