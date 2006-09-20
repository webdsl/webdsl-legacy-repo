package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import coreservlets.beans.*;
import webdsl.users.*;

import org.apache.commons.beanutils.BeanUtils;

/** Simple servlet used to test server.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class Login extends HttpServlet 
{

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	
	response.setContentType("text/html");
	
	PrintWriter out = response.getWriter();
	
	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	    "Transitional//EN\">\n";

	String answer;

        answer = loginUser(request);

	out.println(docType +
		    "<HTML>\n" +
		    "<HEAD><TITLE>Login</TITLE></HEAD>\n" +
		    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		    "<H1>Login</H1>\n" +
                    answer +
		    "</BODY></HTML>");
    }

    private String loginUser(HttpServletRequest request)
    {
	UserInfo userinfo = new UserInfo();

	BeanUtilities.populateBean((Object)userinfo, request);

	String login_succesful = "";
	int count = 0;
	String username = userinfo.getUsername();
	String password = userinfo.getPassword(); 

	login_succesful = 
	    "username = " + username + "\n"
	    + "password = " + password + "\n";

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
	    System.out.println("could not load jdbc driver class");
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
	ResultSet rs;
	
	try {
	    stmt = connection.createStatement();

	    String query = 
		"SELECT user_name, password FROM user "
		+ "WHERE user_name = '" + username + "'"
		+ " && password = '" + password + "'";

	    login_succesful = login_succesful 
		+ "query = "
		+ query;

	    rs = stmt.executeQuery(query);

	    while(rs.next())
		{
		    login_succesful = login_succesful 
			+ "result: "
			+ rs.getString(1)
			+ "/" 
			+ rs.getString(2);
		    count = count + 1;
		}

		

	} catch(Exception ex) {
	} finally {
	    if (stmt != null) {
		try { stmt.close(); } catch (SQLException sqlEx) { ; }
		stmt = null;
	    }
	}
	
	try {
	    connection.close();
	} catch(Exception ex) {}

	
	if(count == 1) 
	    login_succesful = "ok";

	return login_succesful;
    }

}
