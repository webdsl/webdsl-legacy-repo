package org.webdsl.wiki.servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.hibernate.*;
import org.webdsl.wiki.domain.*;
import org.webdsl.wiki.utilities.*;
import org.webdsl.wiki.servlets.*;

import org.apache.commons.beanutils.BeanUtils;

public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("continuation") == null) {
			session.setAttribute("continuation", "/wiki1/view");
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/classes/org/webdsl/wiki/servlets/LoginForm.jsp");
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		User userbean = new User();
		
		BeanUtilities.populateBean((Object) userbean, request);

		User user = User.getByName(userbean.getUsername());

		if (user == null)
		{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no user " + userbean.getUsername()); 
					//+ " (param = " + param_username + ", map = " + map_username[0] + ")");
		} else if (user.getPassword().equals(userbean.getPassword())) {
			session.setAttribute("user", user);
			session.setAttribute("username", user.getUsername());
			String continuation = (String) session.getAttribute("continuation");
			if (continuation != null) {
				session.setAttribute("continuation", null);
				response.sendRedirect(continuation);
			} else
				response.sendRedirect("/wiki1/user/" + user.getUsername());
		} else {
			response.sendError(response.SC_UNAUTHORIZED, "password incorrect: " 
					+ user.getPassword() 
					+ " <> " 
					+ userbean.getPassword());
		}
	}

}
