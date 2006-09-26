package webdsl.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

public class PreviewTopic extends HttpServlet 
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
	getTopicFromDB(topicname, topicinfo);
       		
	request.setAttribute("topicinfo", topicinfo);
	RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/WEB-INF/classes/webdsl/topics/TopicView.jsp");

	if (true) // topicinfo.isComplete())
	    {
		dispatcher.forward(request, response);
	    }
	else 
	    {
		dispatcher.forward(request, response);
		// note: something is wrong here; no user in database?
	    }
    }

    TopicInfo getTopicFromDB(String topicname, TopicInfo topicinfo)
    {
	String query = 
	    "SELECT topicname, topictext FROM user " 
	    + "WHERE topicname = '" + topicname + "'";
	
	return (TopicInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeTopicInfoFromResultSet(topicinfo));
    }
}

