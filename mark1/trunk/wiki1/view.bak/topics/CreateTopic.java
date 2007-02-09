package webdsl.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

public class CreateTopic extends HttpServlet 
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
	String topicname = request.getPathInfo(); 
	if(topicname != null && topicname.startsWith("/"))
	    {
		topicname = topicname.substring(1);
	    }
	else
	    {
		topicname = "";
	    }
	
	TopicInfo topicinfo = new TopicInfo();
	topicinfo.setTopicname(topicname);
	topicinfo.setTopictext("");

	if (topicinfo.addToDatabase() == 1)
	    response.sendRedirect("/user1/view/" + topicname);
	else
	    response.sendRedirect("/user1/view/CreateError?topicname=" + topicname);
    }

}

