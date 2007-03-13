

package org.webdsl.wiki.viewers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.webdsl.wiki.domain.Topic;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WikiContentHandler extends DefaultHandler
{
  private ServletRequest request;

  private Writer out;

  public WikiContentHandler(ServletRequest request, Writer out)
  {
    this.request = request;
    this.out = out;
  }

  public void characters(char[] chars, int lo, int hi) throws SAXException
  {
    try
      {
        // TODO may need to restrict output in some states
        for (int i = lo; i < hi; i++)
          out.write(chars[i]);
      }
    catch (IOException e)
      {
        throw new SAXException(e);
      }
  }

  public void endDocument() throws SAXException
  {
    // do nothing
  }

  public void startElement(String uri, String localname, String qname,
                           Attributes atts) throws SAXException
  {
    try
      {
        // TODO handle special cases
        // default case: copy elements as is
        out.write("<" + localname);
        if (atts != null)
          {
            for (int i = 0; i < atts.getLength(); i++)
              {
                out.write(" " + atts.getLocalName(i) + "=\"" + atts.getValue(i)
                          + "\"");
              }
          }
        out.write(">");
      }
    catch (IOException e)
      {
        throw new SAXException(e);
      }
  }

  public void endElement(String uri, String localname, String qname)
      throws SAXException
  {
    // TODO action depending on element;
    // default case: copy elements as is
    try
      {
        out.write("</" + localname + ">");
      }
    catch (IOException e)
      {
        throw new SAXException(e);
      }
  }

  public void endPrefixMapping(String arg0) throws SAXException
  {
    // do nothing
  }

  public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
      throws SAXException
  {
    // do nothing
  }

  public void processingInstruction(String arg0, String arg1)
      throws SAXException
  {
    // do nothing
  }

  public void setDocumentLocator(Locator arg0)
  {
    // do nothing
  }

  public void skippedEntity(String arg0) throws SAXException
  {
    // do nothing
  }

  public void startDocument() throws SAXException
  {
    // TODO initialization?

  }

  public void startPrefixMapping(String arg0, String arg1) throws SAXException
  {
    // do nothing
  }

  private void includeTopic(String topicname) throws IOException
  {
    if (topicname == null)
      throw new RuntimeException("no topicname for includeTopic");

    String TOPIC = (String) request.getAttribute("TOPIC");
    String WEB = (String) request.getAttribute("WEB");
    String SPACEDTOPIC = (String) request.getAttribute("SPACEDTOPIC");
    String INCLUDINGTOPIC = (String) request.getAttribute("INCLUDINGTOPIC");
    String INCLUDINGWEB = (String) request.getAttribute("INCLUDINGWEB");

    if (!topicname.startsWith("/"))
      {
        topicname = WEB + topicname;
      }

    HashSet includemap = (HashSet) request.getAttribute("includemap");
    if (includemap == null)
      {
        throw new RuntimeException("no includemap");
      }
    if (includemap.contains(topicname))
      {
        out.write("<br>" + (String) request.getAttribute("BASETOPIC")
                  + " has recursive include of " + TOPIC);
        return;
      }
    else
      {
        includemap.add(topicname);
      }

    Topic topicinfo = new Topic();
    // topicinfo.setTopicname(topicname);
    // topicinfo.getFromDatabase();
    if (false) // topicinfo.getTopictext() != null)
      {
        request.setAttribute("TOPIC", topicname);
        // request.setAttribute("WEB", Topic.webName(topicname));
        request.setAttribute("SPACEDTOPIC", topicname);
        request.setAttribute("INCLUDINGTOPIC", TOPIC);
        request.setAttribute("INCLUDINGWEB", WEB);

        // topicinfo.renderTopicText(request, response);

        request.setAttribute("TOPIC", TOPIC);
        request.setAttribute("WEB", WEB);
        request.setAttribute("SPACEDTOPIC", SPACEDTOPIC);
        request.setAttribute("INCLUDINGTOPIC", INCLUDINGTOPIC);
        request.setAttribute("INCLUDINGWEB", INCLUDINGWEB);
      }
    else
      {
        // out.write(topicname + " not found in database <br>");
      }
  }

  private void listTopics(String topicname) throws IOException
  {
    if (topicname == null)
      throw new RuntimeException("no topicname for listTopic");

    Topic topicinfo = new Topic();
    // topicinfo.setTopicname(topicname);

    Map<String,Topic> topics = topicinfo.getSubtopics();

    String prefix = (String) request.getAttribute("SCRIPTURL");

    out.write("<ul>");

    for (String name:topics.keySet())
      {
        out.write("<li> <a href=\"" + prefix + "/view" + name + "\">" + name
                  + "</a></li>");
      }

    out.write("</ul>");
  }

  private String evaluateSimpleVariable(String varname)
  {
    if (varname == null)
      return null;
    else
      return (String) request.getAttribute(varname);
  }
}
