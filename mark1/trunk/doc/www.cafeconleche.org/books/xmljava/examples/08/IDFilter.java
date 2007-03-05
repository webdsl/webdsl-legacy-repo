import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;


public class IDFilter extends XMLFilterImpl {

  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) throws SAXException {    

    boolean hasID = false;
    for (int i = 0; i < atts.getLength(); i++) {
      if (atts.getQName(i).equalsIgnoreCase("id") ||
       atts.getType(i).equals("ID")) {
         hasID = true;
         ids.add(atts.getValue(i));
         break; 
      }
    } 

    if (!hasID) {
      AttributesImpl newAttributes = new AttributesImpl(atts);
      String idValue = makeID();
      newAttributes.addAttribute("", "id", "id", "ID", idValue);
      atts = newAttributes;
    }
    super.startElement(namespaceURI, localName, qualifiedName, 
     atts);

  }
  
  // need to track which IDs we've already used, including IDs
  // that were included in the document
  int id = 1;
  private Set ids; // requires Java 1.2
  
  public void startDocument() {
    // reinitialize id list for each document
    ids = new HashSet();
    id = 1;
  }
  
  // Generate an ID that hasn't been used yet
  private String makeID() {
    
    while (ids.contains("_" + id)) id++;
    ids.add("_" + id);
    return "_" + id;
    
  }
  
}
