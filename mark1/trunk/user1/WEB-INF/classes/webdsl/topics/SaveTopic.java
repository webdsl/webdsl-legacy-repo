package webdsl.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;
import webdsl.topics.*;

public class SaveTopic extends HttpServlet 
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
	TopicInfo topicinfo = new TopicInfo();
	BeanUtilities.populateBean((Object)topicinfo, request);

	if (topicinfo.changeInDatabase() == 1 || topicinfo.addToDatabase() == 1)
	    response.sendRedirect("/user1/view/" + topicinfo.getTopicname());
	else
	    response.sendRedirect("/user1/view/SaveError?topicname=" 
				  + topicinfo.getTopicname());
    }

}
