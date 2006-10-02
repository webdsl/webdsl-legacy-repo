package webdsl.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.topics.*;
import webdsl.html.*;

public class EditTopic extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	String topicname = request.getPathInfo();
	if(topicname == null || topicname.equals(""))
	    {
		response.sendRedirect("/user1/edit/");
		return;
	    }

	TopicInfo topicinfo = new TopicInfo();
	topicinfo.setTopicname(topicname);
	topicinfo.getFromDatabase();
       
	if (topicinfo.getTopictext() == null)
	    {
		topicinfo.setTopictext("");
		topicinfo.addToDatabase();
	    }

	request.setAttribute("topicinfo", topicinfo);

	RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/WEB-INF/classes/webdsl/topics/EditTopicForm.jsp");
	dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doGet(request, response);
    }

}
