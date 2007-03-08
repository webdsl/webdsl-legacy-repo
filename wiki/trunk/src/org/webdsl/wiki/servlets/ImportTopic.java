package org.webdsl.wiki.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

import org.webdsl.wiki.domain.*;
import org.webdsl.wiki.utilities.*;
import org.webdsl.wiki.servlets.*;
import org.webdsl.wiki.xml.*;


public class ImportTopic extends HttpServlet 
{

    public void doGet(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException 
    {
	HttpSession session = request.getSession();
	User user = (User)session.getAttribute("user");
	String path = request.getPathInfo() == null ? "" : request.getPathInfo();
	request.setAttribute("path", path);

	if (user == null)
        {
	  session.setAttribute("continuation", "/wiki1/import" + path);
	  response.sendRedirect("/wiki1/login");
    	} 
        else 
        {
	  RequestDispatcher dispatcher =
	    request.getRequestDispatcher("/WEB-INF/classes/org/webdsl/wiki/servlets/ImportTopicForm.jsp");
	  dispatcher.forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request,
		      HttpServletResponse response)
	throws ServletException, IOException
    {
	HttpSession session = request.getSession();
	User user = (User)session.getAttribute("user");
	String path = request.getPathInfo() == null ? "" : request.getPathInfo();

	if (user == null)
        {
	  session.setAttribute("continuation", "/wiki1/import" + path);
	  response.sendRedirect("/wiki1/login");
    	} 
        else 
        {
	  Session hsession = HibernateUtil.getSessionFactory().openSession();
	  Transaction transaction = hsession.beginTransaction();
	
	  user = (User)hsession.merge(user);
	  Topic root = RootTopic.getRootTopic(hsession);
	  TopicPath topicpath = new TopicPath(root, path, user);
	  Topic topic = (Topic)topicpath.get(topicpath.size() - 1);

	  String topicname = null;

	  if (topic == null)
	  {
	     hsession.close();
	     request.setAttribute("script", "import");
	     request.setAttribute("error", "topic " + path + " does not exist");
	     RequestDispatcher dispatcher =
	      request.getRequestDispatcher(
                "/WEB-INF/classes/view/errors/Error.jsp"
              );
	     dispatcher.forward(request, response);
	     return;
	  } 
          else if (ServletFileUpload.isMultipartContent(request))
          {

            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
	    List /* FileItem */ items;
	    try {
             items = upload.parseRequest(request);
 	    } catch (org.apache.commons.fileupload.FileUploadException e) {
    	       throw new ServletException(e);
	    }

            // Process the uploaded items
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                  if ("topicname".equals(item.getFieldName())) {
                    topicname = item.getString();
 		  }
                } else if( "topicfile".equals(item.getFieldName()) ) {
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();

		    log("uploading topic file " + fileName + " : " 
						+ contentType + " : " 
						+ isInMemory + " : " 
						+ sizeInBytes );

                    InputStream uploadedStream = item.getInputStream();
                    
	            try {
	              FromXML h = FromXML.parse(uploadedStream);
	              Topic subtopic = h.getTopic();
	              topic.addSubtopic(topicname, subtopic);
                      transaction.commit();
	              response.sendRedirect("/wiki1/view" + path + "/" + topicname);
 	            } catch (java.io.UTFDataFormatException e) {
	     		request.setAttribute("script", "import");
	     		request.setAttribute("error", 
				"XML is not valid UTF-8;" 
				+ "convert file to UTF-8 before importing");
	     		RequestDispatcher dispatcher =
	      		  request.getRequestDispatcher(
                		"/WEB-INF/classes/view/errors/Error.jsp"
              		  );
	     		dispatcher.forward(request, response);

 	            } catch (org.xml.sax.SAXException e) {
	              	throw new ServletException(e);
  	            } finally {
	              hsession.close();
                    }

                    uploadedStream.close();
                 }
            }

          }
        }
    }

}
