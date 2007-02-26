package view.errors;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ShowError extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	  RequestDispatcher dispatcher =
	      request.getRequestDispatcher(
                "/WEB-INF/classes/view/errors/Error.jsp"
              );
	  dispatcher.forward(request, response);
    }

}
