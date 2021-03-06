

package org.webdsl.wiki.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webdsl.wiki.domain.RootTopic;
import org.webdsl.wiki.domain.Topic;
import org.webdsl.wiki.domain.TopicDoesNotExistException;
import org.webdsl.wiki.domain.TopicPath;
import org.webdsl.wiki.utilities.HibernateUtil;
import org.webdsl.wiki.viewers.*;

public class ViewTopic extends HttpServlet
{

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    String path = request.getPathInfo() == null ? "" : request.getPathInfo();

    Session hsession = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = hsession.beginTransaction();

    Topic root = RootTopic.getRootTopic(hsession);

    try
      {
        TopicPath topicpath = new TopicPath(root, path);
        Topic topic = (Topic) topicpath.get(topicpath.size() - 1);

        request.setAttribute("path", path);
        request.setAttribute("pathelements", topicpath.getElements());
        request.setAttribute("topic", topic);

        String mimetype = topic.getMimetype();

        log("getting viewer");
        Viewer viewer = ViewerFactory.getViewer(mimetype);

        log("getting text");
        request.setAttribute("text", viewer.makeView(topic));
        request.setAttribute("viewer", viewer);
        
        log("getting subtopics");
        request.setAttribute("subtopics", topic.getSubtopics().keySet());
        request.setAttribute("authors", topic.getAuthorNames());
        
        String viewerServlet = ViewerFactory.getViewerServlet(mimetype);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewerServlet);
        dispatcher.forward(request, response);

        transaction.commit();
        hsession.close();
      }
    catch (TopicDoesNotExistException e)
      {
        transaction.rollback();
        hsession.close();
        response.sendRedirect("/wiki1/edit/" + path);
      }

    // String topicweb = topic.getWebName();

    // request.setAttribute("SCRIPTURL", request.getContextPath());

    // top-level including page
    // request.setAttribute("BASETOPIC", topicname);
    // request.setAttribute("BASEWEB", topicweb);
    // request.setAttribute("BASEPARENTWEB", topic.getParentWeb());

    // page where current page is included in
    // request.setAttribute("INCLUDINGTOPIC", topicname);
    // request.setAttribute("INCLUDINGWEB", topicweb);

    // referring to the page we're currently rendering
    // request.setAttribute("TOPIC", topicname);
    // request.setAttribute("WEB", topicweb);
    // request.setAttribute("SPACEDTOPIC", topicname);

    // request.setAttribute("HOMETOPIC", topicweb + "WebHome");
    // request.setAttribute("MAINWEB", "/");

    // HttpSession session = request.getSession();
    // request.setAttribute("USERNAME", session.getAttribute("USERNAME"));

    // actual page is included from skin page

    // String skin = request.getParameter("skin");
    // if (skin == null)
    // {
    // skin = (String)session.getAttribute("SKIN");
    // if (skin == null)
    // {
    // skin = "/Skins/FlexibleSkin";
    // }
    // }

    // Topic skintopic = new Topic();
    // skintopic.setTopicname(skin);
    // skintopic.getFromDatabase();
    // response.setContentType("text/html");

    // HashSet includemap = new HashSet();
    // includemap.add(skin);
    // request.setAttribute("includemap", includemap);

    // skintopic.renderTopicText(request, response);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    doPost(request, response);
  }

}
