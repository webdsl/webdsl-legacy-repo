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

	if (true) // topicinfo.isComplete())
	    {
		String result = changeTopicInDB(topicinfo);
		response.sendRedirect("/user1/view/" + topicinfo.getTopicname());
	    }
	else 
	    {
		//request.setAttribute("topicinfo", topicinfo);
		//request.setAttribute("next", "/user1/save-user");
		//request.setAttribute("title", "Change Profile of " + topicinfo.getTopicname());
		//request.setAttribute("button", "Save");
		//RequestDispatcher dispatcher =
		//    request.getRequestDispatcher("/WEB-INF/classes/webdsl/users/UserEntryForm.jsp");
		//dispatcher.forward(request, response);
	    }
    }

    private String changeTopicInDB(TopicInfo topicinfo)
    {
	String query = 
	    "update topic set "
	    + "topictext = '" + topicinfo.getTopictext() + "' "
	    + "where topicname = '" + topicinfo.getTopicname() + "';";
	return DataBaseUtilities.updateDataBase(query);
    }
}
