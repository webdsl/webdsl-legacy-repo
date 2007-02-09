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

public class RawTopic extends HttpServlet 
{

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	String topicname = request.getPathInfo();
	String topicweb = TopicInfo.webName(topicname);
	HttpSession session = request.getSession();

	TopicInfo topicinfo = new TopicInfo();
	topicinfo.setTopicname(topicname);
	topicinfo.getFromDatabase();

	response.setContentType("text/css");
	// mime type should be obtained from meta data
	
	PrintWriter out = response.getWriter();
 
	out.write(topicinfo.getTopictext());
    }

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doPost(request, response);
    }

}
