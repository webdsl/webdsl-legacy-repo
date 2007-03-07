package view.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.hibernate.*;

import users.*;
import view.users.*;
import view.html.*;
import util.HibernateUtil;
import util.BeanUtilities;
import util.ServletUtilities;

public class Login extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	HttpSession session = request.getSession();

	if (session.getAttribute("continuation") == null)
	  { session.setAttribute("continuation", "/wiki1/view"); }

	RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/WEB-INF/classes/view/users/LoginForm.jsp");
	dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	HttpSession session = request.getSession();

	User userbean = new User();
	BeanUtilities.populateBean((Object)userbean, request);

	User user = User.getByName(userbean.getUsername());

	if (user != null && user.getPassword().equals(userbean.getPassword()))
	  {
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
	    String continuation = (String)session.getAttribute("continuation");
	    if (continuation != null)
	      { 
		session.setAttribute("continuation", null);
		response.sendRedirect(continuation);
	      }
	    else
	      response.sendRedirect("/wiki1/user/" + user.getUsername());
          }
	else
          {
	    response.sendError(response.SC_UNAUTHORIZED, 
			       "wrong username or password");
          }
    }

}

