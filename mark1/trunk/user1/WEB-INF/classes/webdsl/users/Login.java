package webdsl.users;

import java.io.*;
import java.lang.*;
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
	
	HttpSession session = request.getSession();

	Integer answer = loginUser(request, session);

	response.setContentType("text/html");
	
	PrintWriter out = response.getWriter();
	
	String docType =
	    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	    "Transitional//EN\">\n";

	out.println(docType +
		    "<HTML>\n" +
		    "<HEAD><TITLE>Login</TITLE></HEAD>\n" +
		    "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		    "<H1>Login</H1>\n" +
		    "Logged in as " + 
		    session.getAttribute("username") +
		    "</BODY></HTML>");
    }

    private Integer loginUser(HttpServletRequest request, HttpSession session)
    {
	UserInfo userinfo = new UserInfo();
	BeanUtilities.populateBean((Object)userinfo, request);

	String query = 
	    "SELECT username, password FROM user "
	    + "WHERE username = '" + userinfo.getUsername() + "'"
	    + " && password = '" + userinfo.getPassword() + "'";

	Integer count = 
	    (Integer)DataBaseUtilities.queryDataBase(query, new CountResultSet());

	if (count.intValue() == 1) 
	    session.setAttribute("username", userinfo.getUsername() + count);
	else
	    session.setAttribute("username", "guest" + count);

	return count;
    }

}

class CountResultSet implements ProcessResultSet
{
    public Object process(ResultSet rs) 
    {
	int count = 0;
	try{
	    while(rs.next()) { count = count + 1; }
	} catch(Exception e) {
	    count = -1;
	}
	return (Object) new Integer(count);
    }
}
