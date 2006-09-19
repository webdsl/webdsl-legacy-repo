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

	String user = "no information available about this user";

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
	    rs = stmt.executeQuery("SELECT user_name, name, email FROM user WHERE user_name = '" + user_name + "'");
	    if(rs.next())
		{
		    user = "information for user: " + user_name 
			+ "<ul>"
			+ "<li> user_name: " + rs.getString(1) + "</li>"
			+ "<li> name: "      + rs.getString(2) + "</li>"
			+ "<li> email: "     + rs.getString(3) + "</li>"
			+ "</ul>";
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

	try {
	    connection.close();
	} catch(Exception ex) {}

	return user;
    }

}
