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

	String answer = addTopicToDB(topicinfo);

	response.sendRedirect("/user1/view/" + topicname);
    }

    private String addTopicToDB(TopicInfo topicinfo)
    {
	String query = 
	    "INSERT INTO topic (topicname, topictext) VALUES"
	    + "('"   + topicinfo.getTopicname()
	    + "','"   + topicinfo.getTopictext()
	    + "');";
	
	return DataBaseUtilities.updateDataBase(query);
    }
}

