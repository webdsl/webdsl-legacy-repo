package xml;

import java.io.*;
import java.util.*;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

import com.ibm.icu.text.CharsetDetector;

import org.hibernate.*;
import util.HibernateUtil;

import topics.*;
import users.*;

public class FromXML extends DefaultHandler
{
    public static FromXML parse(String file)
	 throws java.io.FileNotFoundException, 
		org.xml.sax.SAXException,
		java.io.IOException
    {
	FileReader r = new FileReader(file);
	return parse(new InputSource(r));
    }

    public static FromXML parse(InputStream is)
	 throws java.io.FileNotFoundException, 
		org.xml.sax.SAXException,
		java.io.IOException
    {
	//CharsetDetector detector = new CharsetDetector();
	// Reader r = detector.getReader(is, "UTF-8");
	return parse(new InputSource(is));
    }

    public static FromXML parse(Reader r)
	 throws java.io.FileNotFoundException, 
		org.xml.sax.SAXException,
		java.io.IOException
    {
	return parse(new InputSource(r));
    }

    public static FromXML parse(InputSource source)
	 throws java.io.FileNotFoundException, 
		org.xml.sax.SAXException,
		java.io.IOException
    {
	XMLReader xr = XMLReaderFactory.createXMLReader();
	FromXML handler = new FromXML();
	xr.setContentHandler(handler);
	xr.setErrorHandler(handler);
	xr.parse(source);
	return handler;
    }

    public FromXML ()
    {
	super();
    }

    // state variables

    private Topic topic; // the current topic
    private LinkedList topics; // the stack of topics

    private String topicname; // name of the current topic
    private LinkedList topicnames;  // stack of names

    private User user;
    private Map users;

    private StringBuffer chars;

    // properties

    public Topic getTopic() {
      return topic;
    }

    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////


    public void startDocument ()
    {
	users       = new HashMap();
        topics      = new LinkedList();
	topicnames  = new LinkedList();
	user        = null;
	topic       = null;
   	topicname   = null;
	chars       = null;
    }

    public void endDocument ()
    {
    }

    public void characters (char ch[], int start, int length)
    {
	chars.append(ch, start, length);
    }

    public void startElement (String uri, String name,
			      String qName, Attributes atts)
    {
	if ("topic".equals (name))
	  {
	    topic = new Topic();
	    topicname = atts.getValue("name");
	  }
	else if ("subtopics".equals (name))
	  {
	    topics.addFirst(topic);
	    topicnames.addFirst(topicname);
	  }
	else if ("user".equals (name))
	  {
	    user = new User();
	  }
	else 
	  {
	    chars = new StringBuffer();
	  }
    }


    public void endElement (String uri, String name, String qName)
    {
	if ("topic".equals (name))
	  {
	    if (topics.size() > 0 && name != null)
   	      {
		Topic parent = (Topic)topics.getFirst();
		parent.addSubtopic(topicname, topic);
	      }
	  }
	else if ("subtopics".equals (name))
	  {
	    topic = (Topic)topics.removeFirst();
	    topicname = (String)topicnames.removeFirst();
	  }
	else if ("user".equals (name))
	  {
	    users.put(user.getUsername(), user);
	  }

	else if ("username".equals (name))
	  {
	    user.setUsername(chars.toString());
	  }
	else if ("fullname".equals (name))
	  {
	    user.setFullname(chars.toString());
	  }
	else if ("email".equals (name))
	  {
	    user.setEmail(chars.toString());
	  }
	else if ("url".equals (name))
	  {
	    user.setUrl(chars.toString());
	  }
	else if ("password".equals (name))
	  {
	    user.setPassword(chars.toString());
	  }

	else if ("title".equals (name))
	  {
	    topic.setTitle(chars.toString());
	  }
	else if ("text".equals (name))
	  {
	    topic.setText(chars.toString());
	  }
	else if ("mimetype".equals (name))
	  {
	    topic.setMimetype(chars.toString());
	  }
	else if ("author".equals (name))
	  {
	    User user = (User)users.get(chars.toString());
	    if (user != null)
	      topic.addAuthor(user);
	  }
    }

}

