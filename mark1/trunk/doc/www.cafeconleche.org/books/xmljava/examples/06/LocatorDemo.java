import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;


public class LocatorDemo implements ContentHandler {

  private Locator locator;
  
  public void setDocumentLocator(Locator locator) {
    this.locator = locator;
  }
  
  private void printLocation(String s) { 
    
    int line = locator.getLineNumber();
    int column = locator.getColumnNumber();
    System.out.println(
     s + " at line " + line + "; column " + column
    );
    
  }

  public void startDocument() { 
    printLocation("startDocument()");
  }

  public void endDocument() { 
    printLocation("endDocument()");
  }

  public void startElement(String namespaceURI, String localName, 
   String qualifiedName, Attributes atts) {
    printLocation("startElement()");
  }

  public void endElement(String namespaceURI, String localName, 
  String qualifiedName) {
    printLocation("endElement()");
  }
  
  public void characters(char[] text, int start, int length) {
    printLocation("characters()"); 
  }  
  
  public void startPrefixMapping(String prefix, String uri) {
    printLocation("startPrefixMapping()"); 
  }
  
  public void endPrefixMapping(String prefix) {
    printLocation("endPrefixMapping()"); 
  }
  
  public void ignorableWhitespace(char[] text, int start, 
   int length) {
    printLocation("ignorableWhitespace()");  
  }
  
  public void processingInstruction(String target, String data) {
    printLocation("processingInstruction()");  
  }
  
  public void skippedEntity(String name) {
    printLocation("skippedEntity()");     
  }  
  
  public static void main(String[] args) {
    
    if (args.length == 0) {
      System.out.println("Usage: java SAXSpider URL1"); 
    } 
    String uri = args[0];
    
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader();
      
      // Install the ContentHandler   
      ContentHandler handler = new LocatorDemo();   
      parser.setContentHandler(handler);
      parser.parse(uri);

    }
    catch (Exception e) {
      System.err.println(e);
    }
        
  } // end main
   
} // end LocatorDemo
