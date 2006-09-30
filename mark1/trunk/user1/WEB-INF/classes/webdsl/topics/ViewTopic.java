package webdsl.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

public class ViewTopic extends HttpServlet 
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
	    topicname = "";

	TopicInfo topicinfo = new TopicInfo();

	topicinfo.setTopicname("Templates/MasterTemplate");
	topicinfo.getFromDatabase();
       		
	request.setAttribute("TOPICWEB", topicname);
	request.setAttribute("TOPICNAME", topicname);
	request.setAttribute("TOPICTITLE", topicname);

	response.setContentType("text/html");

	topicinfo.renderTopicText(request, response);
    }

}
