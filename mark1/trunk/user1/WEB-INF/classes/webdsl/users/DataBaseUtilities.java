package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

class DataBaseUtilities
{

    public static String updateDataBase(String query)
    {
	// load the jdbc driver

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
	    return "could not load jdbc driver class";
        }

	// make a connection with the database

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
