import org.xml.sax.*;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;


public class CommentReader implements LexicalHandler {

  public void comment (char[] text, int start, int length)
   throws SAXException {

    String comment = new String(text, start, length);
    System.out.println(comment);

  }

  public static void main(String[] args) {

    // set up the parser
    XMLReader parser;
    try {
      parser = XMLReaderFactory.createXMLReader();
    }
    catch (SAXException e) {
      System.err.println("Error: could not locate a parser.");
      System.err.println(
       "Try setting the org.xml.sax.driver system property to "
       + "the fully package qualified name of your parser class."
      );
      return;
    }

    // turn on comment handling
    try {
      LexicalHandler handler = new CommentReader();
      parser.setProperty(
       "http://xml.org/sax/properties/lexical-handler", handler);
    }
    catch (SAXNotRecognizedException e) {
      System.err.println(
       "Installed XML parser does not provide lexical events...");
      return;
    }
    catch (SAXNotSupportedException e) {
      System.err.println(
       "Cannot turn on comment processing here");
      return;
    }

    if (args.length == 0) {
      System.out.println("Usage: java CommentReader URL");
    }

    // start parsing...
    try {
      parser.parse(args[0]);
    }
    catch (SAXParseException e) { // well-formedness error
      System.out.println(args[0] + " is not well formed.");
      System.out.println(e.getMessage()
       + " at line " + e.getLineNumber()
       + ", column " + e.getColumnNumber());
    }
    catch (SAXException e) { // some other kind of error
      System.out.println(e.getMessage());
    }
    catch (IOException e) {
      System.out.println("Could not read " + args[0]
       + " because of the IOException " + e);
    }

  }

  // do-nothing methods not needed in this example
  public void startDTD(String name, String publicId, 
   String systemId) throws SAXException {}
  public void endDTD() throws SAXException {}
  public void startEntity(String name) throws SAXException {}
  public void endEntity(String name) throws SAXException {}
  public void startCDATA() throws SAXException {}
  public void endCDATA() throws SAXException {}

}
