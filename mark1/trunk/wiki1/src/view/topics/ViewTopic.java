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

public class ViewTopic extends HttpServlet 
{

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	TopicInfo topicinfo = new TopicInfo();
	String topicname = request.getPathInfo();
	if (topicname == null || topicname.equals(""))
	    {
		response.sendRedirect("/user1/view/");
		return;
	    }

	topicinfo.setTopicname(topicname);
	String topicweb = topicinfo.getWebName();


	request.setAttribute("SCRIPTURL",      request.getContextPath());

	// top-level including page
	request.setAttribute("BASETOPIC",      topicname);
	request.setAttribute("BASEWEB",        topicweb);
	request.setAttribute("BASEPARENTWEB",  topicinfo.getParentWeb());

	// page where current page is included in
	request.setAttribute("INCLUDINGTOPIC", topicname);
	request.setAttribute("INCLUDINGWEB",   topicweb);

	// referring to the page we're currently rendering
	request.setAttribute("TOPIC",          topicname);
	request.setAttribute("WEB",            topicweb);
	request.setAttribute("SPACEDTOPIC",    topicname);

	request.setAttribute("HOMETOPIC", topicweb + "WebHome");
	request.setAttribute("MAINWEB", "/");


	HttpSession session = request.getSession();
	request.setAttribute("USERNAME", session.getAttribute("USERNAME"));
      
	
	// actual page is included from skin page

	String skin = request.getParameter("skin");
	if (skin == null)
	    {
		skin = (String)session.getAttribute("SKIN");
		if (skin == null)
		    {
			skin = "/Skins/FlexibleSkin";
		    }
	    }

	TopicInfo skintopic = new TopicInfo();
	skintopic.setTopicname(skin);
	skintopic.getFromDatabase();
	response.setContentType("text/html");

	HashSet includemap = new HashSet();
	includemap.add(skin);
	request.setAttribute("includemap", includemap);

	skintopic.renderTopicText(request, response);
    }

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	doPost(request, response);
    }

}
