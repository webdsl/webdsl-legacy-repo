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
	//getTopicFromDB(topicname, topicinfo);
	getTopicFromDB("Templates/MasterTemplate", topicinfo);
       		
	//request.setAttribute("topicinfo", topicinfo);

	request.setAttribute("TOPICWEB", topicname);
	request.setAttribute("TOPICNAME", topicname);
	request.setAttribute("TOPICTITLE", topicname);


	response.setContentType("text/html");

	topicinfo.renderTopicText(request, response);

	//RequestDispatcher dispatcher =
	//    request.getRequestDispatcher("/WEB-INF/classes/webdsl/topics/TopicView.jsp");

	//if (topicinfo.getTopictext() != null)
	//    {
		//dispatcher.forward(request, response);
	//    }
	//else 
	//    {
	//	response.sendRedirect("/user1/create/" + topicname);
	//    }
    }

    public static TopicInfo getTopicFromDB(String topicname, TopicInfo topicinfo)
    {
	String query = 
	    "SELECT topicname, topictext FROM topic "
	    + "WHERE topicname = '" + topicname + "'";
	
	return (TopicInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeTopicInfoFromResultSet(topicinfo));
    }
}

class MakeTopicInfoFromResultSet implements ProcessResultSet
{
    TopicInfo topicinfo;

    public MakeTopicInfoFromResultSet(TopicInfo topicinfo)
    {
	this.topicinfo = topicinfo;
    }

    public Object process(ResultSet rs)
    {
	try{
	    if(rs.next())
		{
		    topicinfo.setTopicname(rs.getString(1));
		    topicinfo.setTopictext(rs.getString(2));
		}
	} catch(Exception e) {}
	return (Object) topicinfo;
    }
}
