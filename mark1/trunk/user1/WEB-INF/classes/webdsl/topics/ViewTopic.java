package webdsl.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import coreservlets.beans.*;
import webdsl.users.*;
import webdsl.html.*;
import webdsl.topics.*;

public class ViewTopic extends HttpServlet 
{

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	String topicname = request.getPathInfo();
	String topicweb = TopicInfo.webName(topicname);
	HttpSession session = request.getSession();

	//ServletContext servletcontext = request.getServletContext();
	//servletcontext.setAttribute("SCRIPTURLPATH",servletcontex.getServletContextName());
	//servletcontext.setAttribute("SCRIPTURL",    servletcontex.getRealPath("/"));	

	    /*
  - %ATTACHURL% -- full URL for attachments in the current topic
  - %ATTACHURLPATH% -- path of the attachment URL of the current topic
  - SCRIPTURL -- script URL of TWiki#
    # Expands to: http://abaris.zoo.cs.uu.nl:8080/wiki 
  - SCRIPTURLPATH -- script URL path of TWiki#
    # Expands to: /wiki 
  - PUBURL -- the base URL of attachments#
    Expands to: http://abaris.zoo.cs.uu.nl:8080/wiki/pub 
  - PUBURLPATH -- the base URL path of attachments
    * Expands to: /wiki/pub 
  - HTTP_HOST -- environment variable
	    */

	request.setAttribute("SCRIPTURL",      request.getContextPath());

	// top-level including page
	request.setAttribute("BASETOPIC",      topicname);
	request.setAttribute("BASEWEB",        topicweb);

	// page where current page is included in
	request.setAttribute("INCLUDINGTOPIC", topicname);
	request.setAttribute("INCLUDINGWEB",   topicweb);

	// referring to the page we're currently rendering
	request.setAttribute("TOPIC",          topicname);
	request.setAttribute("WEB",            topicweb);
	request.setAttribute("SPACEDTOPIC",    topicname);

	request.setAttribute("HOMETOPIC", topicweb + "WebHome");
	request.setAttribute("MAINWEB", "/Main/");
	request.setAttribute("USERNAME", session.getAttribute("USERNAME"));
      
	
	// actual page is included from skin page

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
