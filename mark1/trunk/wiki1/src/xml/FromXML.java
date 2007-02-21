package xml;

import java.io.FileReader;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import topics.*;
import users.*;

public class FromXML extends DefaultHandler
{
    public static FromXML parse(String file)
	 throws java.io.FileNotFoundException, 
		org.xml.sax.SAXException,
		java.io.IOException
    {
	XMLReader xr = XMLReaderFactory.createXMLReader();
	FromXML handler = new FromXML();
	xr.setContentHandler(handler);
	xr.setErrorHandler(handler);
	FileReader r = new FileReader(file);
	xr.parse(new InputSource(r));
	return handler;
    }

    public FromXML ()
    {
	super();
    }

    // state variables

    private Topic topic; // the current topic
    private LinkedList topics; // the stack of topics

    private String name; // name of the current topic
    private LinkedList names;  // stack of names

    private User user;
    private Set users;

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
	users  = new HashSet();
        topics = new LinkedList();
	names  = new LinkedList();
	user   = null;
	topic  = null;
   	name   = null;
	chars = new StringBuffer();
    }

    public void endDocument ()
    {
    }

    public void characters (char ch[], int start, int length)
    {
	chars.append(ch);
    }

    public void startElement (String uri, String name,
			      String qName, Attributes atts)
    {
	if ("topic".equals (name))
	  {
	    topic = new Topic();
	    name = atts.getValue("name");
	  }
	else if ("subtopics".equals (name))
	  {
	    topics.addFirst(topic);
	    names.addFirst(name);
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
		parent.addSubtopic(name, topic);
	      }
	  }
	else if ("subtopics".equals (name))
	  {
	    topic = (Topic)topics.removeFirst();
	    name = (String)names.removeFirst();
	  }
	else if ("user".equals (name))
	  {
	    users.add(user);
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
	    //topic.addAuthor(User.getByName(chars.toString()));
	  }
    }

}

