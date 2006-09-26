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
	if(topicname != null && topicname.startsWith("/"))
	    {
		topicname = topicname.substring(1);
	    }
	else
	    {
		response.sendRedirect("/user1");
		// no topicname in pathinfo
	    }

	TopicInfo topicinfo = new TopicInfo();
	topicinfo.setTopicname(topicname);
	getTopicFromDB(topicinfo);
       
	if (true) // test if topic already existed
	    {		
		request.setAttribute("topicinfo", topicinfo);
		//request.setAttribute("next",  "/user1/preview");
		//request.setAttribute("title", 
		//		     "Edit " + topicinfo.getTopicname());
		//request.setAttribute("button", "Preview");

		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/classes/webdsl/topics/EditTopicForm.jsp");
		dispatcher.forward(request, response);
	    }
	else 
	    {
		response.sendError(response.SC_NOT_FOUND, "no such topic");
	    }
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doGet(request, response);
    }

    TopicInfo getTopicFromDB(TopicInfo topicinfo)
    {
	String query = 
	    "SELECT topicname, topictext FROM topic " 
	    + "WHERE topicname = '" + topicinfo.getTopicname() + "'";
	
	return (TopicInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeTopicInfoFromResultSet(topicinfo));
    }
}
