import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.*;


public class DocumentMerger implements ContentHandler {

  private XMLReader parser;
  private Writer out;
  
  public DocumentMerger(XMLReader parser, Writer out) {
    this.parser = parser;
    this.out = out;   
  }
  
  private void output() throws SAXException {
    
    try {
      String s = (String) parser.getProperty(
       "http://xml.org/sax/properties/xml-string");
      out.write(s);
    }
    catch (IOException e) {
      throw new SAXException("Nested IOException", e);  
    }    
    
  }
    
  public void setDocumentLocator(Locator locator) {}
  
  public void startDocument() throws SAXException {
    this.output();
  }
  public void endDocument() throws SAXException {
    this.output();
  }
  
  public void startPrefixMapping(String prefix, String uri)
   throws SAXException {
    this.output();
  }
  
  public void endPrefixMapping(String prefix) 
   throws SAXException {
    this.output();
  }
  
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) throws SAXException {
    this.output();
  }
  
  public void endElement(String namespaceURI, String localName,
   String qualifiedName) throws SAXException {
    this.output();
  }
  
  public void characters(char[] text, int start, int length)
   throws SAXException {
    this.output();
  }
  
  public void ignorableWhitespace(char[] text, int start, 
   int length) throws SAXException {
    this.output();
  }
  
  public void processingInstruction(String target, String data)
   throws SAXException {
    this.output();
  }
  
   
  public void skippedEntity(String name)
   throws SAXException {
    this.output();
  }

  public static void main(String[] args) {
      
    if (args.length <= 0) {
      System.out.println(
       "Usage: java DocumentMerger url"
      );
      return;
    }
          
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader();
      
      // Since this just writes onto the console, it's best
      // to use the system default encoding, which is what
      // we get by not specifying an explicit encoding here.
      Writer out = new OutputStreamWriter(System.out);
      ContentHandler handler = new DocumentMerger(parser, out);
      parser.setContentHandler(handler);
    
      parser.parse(args[0]);
      
      out.flush();
      out.close();
    }
    catch (Exception e) {
      System.err.println(e); 
    }
  
  }   
  
}
