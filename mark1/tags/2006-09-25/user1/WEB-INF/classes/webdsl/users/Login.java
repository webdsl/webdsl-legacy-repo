package webdsl.users;

import java.io.*;
import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

public class Login extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	HttpSession session = request.getSession();

	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	
	HtmlUtilities.printHeader(out, "Login");

	out.println(
           "<form method=\"POST\">"
	   + HtmlUtilities.inputElement("Username", "text", "username", "", true)
	   + "<br />"
	   + HtmlUtilities.inputElement("Password", "password", "password", "", true)
	   + "<br />"
	   );

	out.println("<input type=\"submit\" name=\"login\" />");

	out.println("</form>");

	HtmlUtilities.printActions(out /*, session.getAttribute("username") */);

	HtmlUtilities.printFooter(out);
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	HttpSession session = request.getSession();

	Integer answer = loginUser(request, session);
	
	String username = (String)session.getAttribute("username");
	
	if (answer.intValue() == 1)
	    response.sendRedirect("/user1/user/" + username);
	else
	    response.sendError(response.SC_UNAUTHORIZED, 
			       "wrong username password combination");
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
	    session.setAttribute("username", userinfo.getUsername());
	else
	    ; // throw exception to indicate login failure
	
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
