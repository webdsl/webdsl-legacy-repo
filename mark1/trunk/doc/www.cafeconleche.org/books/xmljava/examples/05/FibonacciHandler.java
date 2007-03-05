import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


public class FibonacciHandler extends DefaultHandler {

  private boolean inDouble = false;
  
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) throws SAXException {
    
    if (localName.equals("double")) inDouble = true;
    
  }

  public void endElement(String namespaceURI, String localName,
   String qualifiedName) throws SAXException {
    
    if (localName.equals("double")) inDouble = false;
    
  }

  public void characters(char[] ch, int start, int length)
  throws SAXException {

    if (inDouble) {
      for (int i = start; i < start+length; i++) {
        System.out.print(ch[i]); 
      }
    }   
   
  }
  
}
