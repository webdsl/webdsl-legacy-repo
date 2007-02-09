package webdsl.topics;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;
import webdsl.topics.*;

public class RawTopics extends HttpServlet 
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
	if(topicname == null)
	    topicname = "/";

	TopicInfo topicinfo = new TopicInfo();
	topicinfo.setTopicname(topicname);
	ArrayList topics = topicinfo.getSubtopics();
       	
	response.setContentType("text/plain");

	PrintWriter out = response.getWriter();

	for (int i = 0; i < topics.size(); i++)
	    {
		out.write(((TopicInfo)topics.get(i)).getTopicname() + "\n");
	    }

	out.close();
    }

}
