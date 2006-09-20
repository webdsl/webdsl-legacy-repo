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

public class ShowUsers extends HttpServlet 
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

	String users = getUsersFromDB();

	out.println(docType +
		    "<HTML>\n" +
		    "<HEAD><TITLE>ShowUsers</TITLE></HEAD>\n" +
		    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		    "<H1>ShowUsers</H1>\n" +
                    users +
		    "</BODY></HTML>");
    }

  String getUsersFromDB() 
    {
	String query = "SELECT user_name, name FROM user";

	return (String)DataBaseUtilities
	    .queryDataBase(query, new MakeUserListFromResultSet());
    }

}


class MakeUserListFromResultSet implements ProcessResultSet
{
    public Object process(ResultSet rs) 
    {
	String users = "";
	try{
	    while(rs.next())
		{
		    users = users 
			+ "<li>"
			+ "<a href=/user1/show-user/" + rs.getString(1) + ">"
			+ rs.getString(2)
			+ "</a>"
			+ "</li>";
		}
	} catch(Exception e) {
	    users = e.toString();
	}
	return (Object) users;
    }
}
