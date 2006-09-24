package webdsl.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import webdsl.html.*;

public class ShowUsers extends HttpServlet 
{

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException 
    {
	String users = getUsersFromDB();

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	HtmlUtilities.printHeader(out, "Registered Users");
	out.println(users);
	HtmlUtilities.printActions(out);
	HtmlUtilities.printFooter(out);
	
    }

  String getUsersFromDB() 
    {
	String query = "SELECT username, name FROM user";
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
			+ "<a href=/user1/user/" + rs.getString(1) + ">"
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
