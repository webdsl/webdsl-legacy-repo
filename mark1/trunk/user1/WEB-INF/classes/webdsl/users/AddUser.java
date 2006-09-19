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

	out.println(docType +
		    "<HTML>\n" +
		    "<HEAD><TITLE>AddUser</TITLE></HEAD>\n" +
		    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		    "<H1>AddUser</H1>\n" +
                    users +
		    "</BODY></HTML>");
    }

    private String addUserToDB(HttpServletRequest request)
    {
	String user_name = request.getParameter("user_name");
	String name = request.getParameter("name");
	String password = request.getParameter("password"); 
	String password_check = request.getParameter("password_check"); 
	String email = request.getParameter("email");

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
	int rows_affected;
	
	try {
	    stmt = connection.createStatement();

	    String query = 
		"INSERT INTO user (user_name, password, name, email) VALUES"
		+ "('"   + user_name
		+ "', '" + password 
		+ "', '" + name
		+ "', '" + email 
		+ "');";

	    rows_affected = stmt.executeUpdate(query);
	} catch(Exception ex) {
	    return ex.toString();
	} finally {
	    if (stmt != null) {
		try { stmt.close(); } catch (SQLException sqlEx) { ; }
		stmt = null;
	    }
	}
	
	try {
	    connection.close();
	} catch(Exception ex) {}

	return "" + rows_affected;
    }

}
