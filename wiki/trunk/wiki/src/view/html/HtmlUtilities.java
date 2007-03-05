package view.html;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class HtmlUtilities 
{
    
    public static String docType() 
    {
	return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " 
	    + "Transitional//EN\">\n";
    }

    public static void printHeader(PrintWriter out, String title)
    {
	out.println(docType()
		    + "<html>\n" 
		    + "<head><title>" + title + "</title></head>\n" 
		    + "<body bgcolor=\"white\">\n" 
		    + "<h1>" + title + "</h1>\n");

    }

    public static void printFooter(PrintWriter out)
    {
	out.println("</body></html>");
    }

    public static void printActions(PrintWriter out /*, String username */)
    {
	out.println("<a href=\"/wiki1/\">Main</a>"
		    + " | <a href=\"/wiki1/login\">Login</a>"
		    + " | <a href=\"/wiki1/logout\">Logout</a>"
		    + " | <a href=\"/wiki1/users\">Users</a>"
		    + " | <a href=\"/wiki1/register-user\">Register</a>"
		    );

	/*
	if (username != null & !username.equals(""))
	    out.println(" | Logged in as <a href=\"/wiki1/user/" 
			+ username 
			+ "\">" 
			+ username 
			+ "</a>");
	*/
    }

    public static String inputElement(String prompt,
				String type,
				String name,
				String value,
				boolean shouldPrompt)
    {
	String message = "";
	if (shouldPrompt)
	    if ((value == null) || value.trim().equals(""))
		message = "<b>" + prompt + "*</b>";
	    else 
		message = prompt + "*";
	else
	    message = prompt;
	
	if (value == null)
	    value = "";

	return
	    (message
	     + ": "
	     + "<input type=\"" + type + "\" name=\"" + name + "\"" 
	     + " value=\"" + value + "\" /><br />\n"
	     );
    }

}
