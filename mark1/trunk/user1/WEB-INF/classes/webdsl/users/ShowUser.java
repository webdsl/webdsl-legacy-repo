package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/** Simple servlet used to test server.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class ShowUser extends HttpServlet 
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

	String user = getUserFromDB(request);

	out.println(docType +
		    "<HTML>\n" +
		    "<HEAD><TITLE>ShowUser</TITLE></HEAD>\n" +
		    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		    "<H1>ShowUser</H1>\n" +
                    user +
		    "</BODY></HTML>");
    }

  String getUserFromDB(HttpServletRequest request)
    {
	String user_name = request.getPathInfo();
	if(user_name.startsWith("/"))
	    user_name = user_name.substring(1);

	String query = 
	    "SELECT user_name, name, email, url FROM user " 
	    + "WHERE user_name = '" + user_name + "'";
    
	return (String)DataBaseUtilities
	    .queryDataBase(query, new MakeUserInfoFromResultSet());
    }
}

class MakeUserInfoFromResultSet implements ProcessResultSet
{
    public Object process(ResultSet rs) 
    {
	String user = "";
	try{
	    if(rs.next())
		{
		    user = ""
			+ "<ul>"
			+ "<li> user_name: " + rs.getString(1) + "</li>"
			+ "<li> name: "      + rs.getString(2) + "</li>"
			+ "<li> email: "     + rs.getString(3) + "</li>"
			+ "<li> url: "     + rs.getString(4) + "</li>"
			+ "</ul>";
		}
	} catch(Exception e) {
	    user = e.toString();
	}
	return (Object) user;
    }
}
