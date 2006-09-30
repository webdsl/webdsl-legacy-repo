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

	if (topicname.endsWith("/"))
	    {
		topicname = topicname + "WebHome";
	    }

	String[] dirs = topicname.split("/");

	StringBuffer topicweb = new StringBuffer();
	
	for (int i = 0; i < dirs.length - 1; i++)
	    {
		topicweb.append(dirs[i]);
		topicweb.append("/");
	    }

	request.setAttribute("TOPICWEB", topicweb.toString());
	request.setAttribute("TOPICNAME", topicname);
	request.setAttribute("TOPICTITLE", topicname);


	TopicInfo topicinfo = new TopicInfo();

	topicinfo.setTopicname("Skins/FlexibleSkin");
	topicinfo.getFromDatabase();


	response.setContentType("text/html");

	topicinfo.renderTopicText(request, response);
    }

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doPost(request, response);
    }

}
