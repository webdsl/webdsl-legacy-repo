package view.topics;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import org.hibernate.*;

import org.apache.commons.fileupload.*;

import users.*;
import topics.*;
import xml.*;
import view.users.*;
import view.html.*;
import util.HibernateUtil;
import util.ServletUtilities;
import util.BeanUtilities;

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
	    request.getRequestDispatcher("/WEB-INF/classes/view/topics/ImportTopicForm.jsp");
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

	  String topicname = (String)request.getAttribute("topicname");

	  if (topic == null)
	  {
	     response.sendRedirect("/wiki1/");
	     hsession.close();
	     return;
	  } 
          else if (ServletFileUpload.isMultipartContent(request))
          {

            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List /* FileItem */ items = upload.parseRequest(request);

            // Process the uploaded items
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                  String name = item.getFieldName();
                  String value = item.getString();

                } else if( "topicfile".equals(item.getFieldName()) ) {
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();

		    log("uploading topic file " + filename + " : " 
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
