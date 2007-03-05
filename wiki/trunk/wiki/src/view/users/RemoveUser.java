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

public class RemoveUser extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	String username = ServletUtilities.getNameFromPathInfo(request.getPathInfo());

	if(username == null) {
	    response.sendError(response.SC_BAD_REQUEST,
			       "no username in pathinfo");
	}

	User user = User.getByName(username);
	if (user != null)
          {
            user.delete();
            response.sendRedirect("/wiki1/users");
          }
        else
          {
	    response.sendError(response.SC_BAD_REQUEST,
			       "user " + username + " does not exist");
          }
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
      doGet(request, response);
    }

}
