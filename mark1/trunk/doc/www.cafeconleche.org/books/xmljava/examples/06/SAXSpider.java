import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.*;


public class SAXSpider extends DefaultHandler {

  // Need to keep track of where we've been 
  // so we don't get stuck in an infinite loop
  private List spideredURIs = new Vector();

  // This linked list keeps track of where we're going.
  // Although the LinkedList class does not guarantee queue like
  // access, I always access it in a first-in/first-out fashion.
  private LinkedList queue = new LinkedList();
  
  private String    currentURI;
  private XMLReader parser;
  
  public SAXSpider(XMLReader parser, String uri) {
    this.parser = parser;
    this.currentURI = uri;
  }
  
  public void endDocument() { 
    
    spideredURIs.add(currentURI);
    System.out.println("Visited " + currentURI);
    String uri;
    try {
      uri = (String) queue.removeLast();
    }
    catch (NoSuchElementException e) {
      // The queue is empty; we're finished.
      return;
    }
    this.currentURI = uri;
    try {
      parser.parse(uri);
    }
    catch (Exception e) { 
      // just skip this one and move on to the next
      this.endDocument();
    }
    
  }

  public void startElement(String namespaceURI, String localName, 
   String qualifiedName, Attributes atts) {
    
    String type 
     = atts.getValue("http://www.w3.org/1999/xlink", "type");
    if (type != null) {
      String href 
       = atts.getValue("http://www.w3.org/1999/xlink", "href");
      if (href != null) {
        if (!spideredURIs.contains(href)) {
          queue.addFirst(href);
        }
      }
    }
    
  }
  

  public static void main(String[] args) {
    
    if (args.length == 0) {
      System.out.println("Usage: java SAXSpider URL1"); 
    } 
    String uri = args[0];
    
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader(
        "org.apache.xerces.parsers.SAXParser"
      );
      
    // Install the ContentHandler   
    ContentHandler spider = new SAXSpider(parser, uri);   
    parser.setContentHandler(spider);
    parser.parse(uri);

    }
    catch (Exception e) {
      System.err.println(e);
    }
        
  } // end main

} // end SAXSpider
