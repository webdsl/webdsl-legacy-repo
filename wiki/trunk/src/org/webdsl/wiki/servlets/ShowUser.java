package org.webdsl.wiki.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import org.webdsl.wiki.domain.*;
import org.webdsl.wiki.utilities.*;
import org.webdsl.wiki.servlets.*;


public class ShowUser extends HttpServlet 
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
	String username = ServletUtilities.getNameFromPathInfo(request.getPathInfo());

	if(username == null) {
           response.sendRedirect("/wiki1");
	}

	User user = User.getByName(username);

	if (user == null) {
           response.sendRedirect("/wiki1/register-user");
	} else {
	  request.setAttribute("userinfo", user);
	  RequestDispatcher dispatcher =
	      request.getRequestDispatcher(
                "/WEB-INF/classes/org/webdsl/wiki/servlets/UserView.jsp"
              );
	  dispatcher.forward(request, response);
  	}
    }

}
