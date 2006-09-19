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
	String users = "no users yet";
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
	    return "could not load jdbc driver class";
        }

	Connection connection;

	try {
            connection = 
		DriverManager
		.getConnection("jdbc:mysql://localhost/users?user=visser&password=dsl");
	    
            // Do something with the Connection
	    
	} catch (SQLException ex) {
            return "no connection to database <br>\n" 
		+ "SQLException: " + ex.getMessage() + "<br>\n"
		+ "SQLState: " + ex.getSQLState() + "<br>\n"
		+ "VendorError: " + ex.getErrorCode();
        }

	Statement stmt = null;
	ResultSet rs = null;
	
	try {
	    stmt = connection.createStatement();
	    rs = stmt.executeQuery("SELECT user_name, name FROM user");
	    users = "";
	    while(rs.next())
		{
		    users = users 
			+ "<li>"
			+ "<a href=/user1/show-user/" + rs.getString(1) + ">"
			+ rs.getString(2)
			+ "</a>"
			+ "</li>";
		}
	} catch(Exception ex) {
	    return ex.toString();
	} finally {
	    // it is a good idea to release
	    // resources in a finally{} block
	    // in reverse-order of their creation
	    // if they are no-longer needed
	    
	    if (rs != null) {
		try { rs.close(); } catch (SQLException sqlEx) { ; }
		rs = null;
	    }		
	    if (stmt != null) {
		try { stmt.close(); } catch (SQLException sqlEx) { ; }
		stmt = null;
	    }
	}

	return users;
    }

}
