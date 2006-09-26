package webdsl.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;

public class ViewTopics extends HttpServlet 
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

	ArrayList topics = new ArrayList();
	getTopicsFromDB(topicname, topics);
       		
	request.setAttribute("topics", topics);
	RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/WEB-INF/classes/webdsl/topics/TopicsView.jsp");
	dispatcher.forward(request, response);
    }

    TopicInfo getTopicsFromDB(String topicname, ArrayList topics)
    {
	String query = 
	    "SELECT topicname FROM topic";
	
	return (TopicInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeTopicInfoArrayListFromResultSet(topics));
    }
}

class MakeTopicInfoArrayListFromResultSet implements ProcessResultSet
{
    ArrayList topics;

    public MakeTopicInfoArrayListFromResultSet(ArrayList topics)
    {
	this.topics = topics;
    }

    public Object process(ResultSet rs)
    {
	try{
	    if(rs.next())
		{
		    TopicInfo topicinfo = new TopicInfo();
		    topicinfo.setTopicname(rs.getString(1));
		    topics.add(topicinfo);
		}
	} catch(Exception e) {}
	return (Object) topics;
    }
}
