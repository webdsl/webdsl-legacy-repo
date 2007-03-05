import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.net.*;
import org.w3c.dom.*;


public class DOMSpider {

  public static String XLINK_NAMESPACE 
   = "http://www.w3.org/1999/xlink";
  
  // This will be used to read all the documents. We could use
  // multiple parsers in parallel. However, it's a lot easier
  // to work in a single thread, and doing so puts some real 
  // limits on how much bandwidth this program will eat.
  private DocumentBuilder parser; 
  
  // Builds the parser
  public DOMSpider() throws ParserConfigurationException {
  
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      parser = factory.newDocumentBuilder();
    }
    catch (FactoryConfigurationError e) { 
      // I don't absolutely need to catch this, but I hate to 
      // throw an Error for no good reason.
      throw new ParserConfigurationException(
       "Could not locate a factory class"); 
    }

  }
  
  // store the URLs already visited
  private Vector visited = new Vector();
  
  // Limit the amount of bandwidth this program uses
  private int maxDepth = 5;
  private int currentDepth = 0; 
  
  public void spider(String systemID) {
    currentDepth++;
    try {
      if (currentDepth < maxDepth) {
        Document document = parser.parse(systemID);
        process(document, systemID);
        
        Vector toBeVisited = new Vector();
        // search the document for uris, 
        // store them in vector, and print them
        findLinks(document.getDocumentElement(), 
         toBeVisited, systemID);
    
        Enumeration e = toBeVisited.elements();
        while (e.hasMoreElements()) {
          String uri = (String) e.nextElement();
          visited.add(uri);
          spider(uri); 
        }
      
      }
    
    }
    catch (SAXException e) {
      // Couldn't load the document, 
      // probably not well-formed XML, skip it 
    }
    catch (IOException e) {
      // Couldn't load the document, 
      // likely network failure, skip it 
    }
    finally { 
      currentDepth--;
      System.out.flush();     
    }
      
  }
  
  public void process(Document document, String uri) {
    System.out.println(uri);
  }
  
  // Recursively descend the tree of one document
  private void findLinks(Element element, List uris, 
   String base) {
    
    // Check for an xml:base attribute
    String baseAtt = element.getAttribute("xml:base");
    if (!baseAtt.equals(""))  base = baseAtt;
    
    // look for XLinks in this element
    if (isSimpleLink(element)) {
      String uri 
       = element.getAttributeNS(XLINK_NAMESPACE, "href");
      if (!uri.equals("")) {
        try {
          String wholePage = absolutize(base, uri);
          if (!visited.contains(wholePage) 
           && !uris.contains(wholePage)) {
            uris.add(wholePage);
          }        
        }
        catch (MalformedURLException e) {
          // If it's not a good URL, then we can't spider it 
          // anyway, so just drop it on the floor.
        }
      } // end if 
    } // end if 
    
    // process child elements recursively
    NodeList children = element.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node node = children.item(i);
      int type = node.getNodeType();
      if (type == Node.ELEMENT_NODE) {
        findLinks((Element) node, uris, base);
      } 
    } // end for
    
  }

  // If you're willing to require Java 1.4, you can do better 
  // than this with the new java.net.URI class
  private static String absolutize(String context, String uri) 
   throws MalformedURLException {
  
    URL contextURL = new URL(context);
    URL url = new URL(contextURL, uri);
    // Remove fragment identifier if any
    String wholePage = url.toExternalForm();
    int fragmentSeparator = wholePage.indexOf('#');
    if (fragmentSeparator != -1) { 
      // There is a fragment identifier
      wholePage = wholePage.substring(0, fragmentSeparator);
    }  
    return wholePage;
    
  }
  
  private static boolean isSimpleLink(Element element) {
  
    String type 
     = element.getAttributeNS(XLINK_NAMESPACE, "type");
    if (type.equals("simple")) return true;
    return false;
    
  }
  
  public static void main(String[] args) {
    
    if (args.length == 0) {
      System.out.println("Usage: java DOMSpider topURL"); 
      return;
    } 
    
    // start parsing... 
    try {
      DOMSpider spider = new DOMSpider();
      spider.spider(args[0]);
    }
    catch (Exception e) {
      System.err.println(e);
      e.printStackTrace(); 
    }
  
  } // end main

} // end DOMSpider
