import org.xml.sax.*;
import org.xml.sax.helpers.*;


public class ResourceToTable implements ContentHandler {

  public final static String RDDL_NAMESPACE 
   = "http://www.rddl.org/";
  public final static String XHTML_NAMESPACE 
   = "http://www.w3.org/1999/xhtml";
  public final static String XLINK_NAMESPACE 
   = "http://www.w3.org/1999/xlink";
  
  private ContentHandler parent;
  
  public ResourceToTable(ContentHandler parent) {
    this.parent = parent;
  }

  // Replace <rddl:resource> start-tags with the beginning of
  // a table. Move the attributes into table cells.
  // Make the content of the element the last table row.
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes atts) throws SAXException {
     
    if (localName.equals("resource") 
        && RDDL_NAMESPACE.equals(namespaceURI)) {
          
      String hrefAtt    = atts.getValue(XLINK_NAMESPACE, "href");    
      String roleAtt    = atts.getValue(XLINK_NAMESPACE, "role");    
      String arcroleAtt 
       = atts.getValue(XLINK_NAMESPACE, "arcrole");    
      String titleAtt   = atts.getValue(XLINK_NAMESPACE, "title");    
      
      // We need to copy the id, xml:lang, and xml:base
      // attributes, if any, from the rddl:resource element to
      // the XHTML table element
      AttributesImpl tableAttributes = new AttributesImpl();
      
      String id = atts.getValue("id");
      if (id != null) {
        tableAttributes.addAttribute("", "id", "id", "ID", id);
      }
      
      String lang = atts.getValue("xml:lang");
      if (lang != null) {
        tableAttributes.addAttribute(NamespaceSupport.XMLNS, 
         "lang", "xml:lang", "NMTOKEN", lang);
      }
      
      String base = atts.getValue("xml:base");
      // xml:base is not legal in XHTML. Here I just drop it, but
      // it would be preferable to use it to resolve URLs in the 
      // XHTML document before passing them along
      
      tableAttributes.addAttribute("", "border", "border", 
       "NMTOKEN", "1");
      parent.startElement(
       XHTML_NAMESPACE, "table", "table", tableAttributes);
      
      Attributes noAtts = new AttributesImpl();
      if (titleAtt != null) {
        parent.startElement(
         XHTML_NAMESPACE, "caption", "caption", noAtts);
        characters(titleAtt.toCharArray(), 0, titleAtt.length());
        endElement(XHTML_NAMESPACE, "caption", "caption");
      }
      
      if (roleAtt != null) {
        parent.startElement(XHTML_NAMESPACE, "tr", "tr", noAtts);
        parent.startElement(XHTML_NAMESPACE, "td", "td", noAtts);
        String role = "Role: ";
        characters(role.toCharArray(), 0, role.length());
        endElement(XHTML_NAMESPACE, "td", "td");
        parent.startElement(XHTML_NAMESPACE, "td", "td", noAtts);
        characters(roleAtt.toCharArray(), 0, roleAtt.length());
        endElement(XHTML_NAMESPACE, "td", "td");
        endElement(XHTML_NAMESPACE, "tr", "tr");
      }

      if (arcroleAtt != null) {
        String arcrole = "Arcrole: ";
        parent.startElement(XHTML_NAMESPACE, "tr", "tr", noAtts);
        parent.startElement(XHTML_NAMESPACE, "td", "td", noAtts);
        characters(arcrole.toCharArray(), 0, arcrole.length());
        endElement(XHTML_NAMESPACE, "td", "td");
        parent.startElement(XHTML_NAMESPACE, "td", "td", noAtts);
        characters(
         arcroleAtt.toCharArray(), 0, arcroleAtt.length());
        endElement(XHTML_NAMESPACE, "td", "td");
        endElement(XHTML_NAMESPACE, "tr", "tr");
      }
      
      if (hrefAtt != null) {
        String href = "href: ";
        AttributesImpl hrefAtts = new AttributesImpl();
        hrefAtts.addAttribute("", "href", "href", "CDATA", href);
        parent.startElement(XHTML_NAMESPACE, "tr", "tr", noAtts);
        parent.startElement(XHTML_NAMESPACE, "td", "td", noAtts);
        characters(href.toCharArray(), 0, href.length());
        endElement(XHTML_NAMESPACE, "td", "td");
        parent.startElement(XHTML_NAMESPACE, "td", "td", noAtts);
        parent.startElement(XHTML_NAMESPACE, "a", "a", hrefAtts);
        characters(hrefAtt.toCharArray(), 0, hrefAtt.length());
        endElement(XHTML_NAMESPACE, "a", "a");
        endElement(XHTML_NAMESPACE, "td", "td");
        endElement(XHTML_NAMESPACE, "tr", "tr");
      }
      
      // Now open the contents of the element
      parent.startElement(XHTML_NAMESPACE, "tr", "tr", noAtts);
      AttributesImpl colspanAtts = new AttributesImpl();
      colspanAtts.addAttribute(
       "", "colspan", "colspan", "CDATA", "2");
      parent.startElement(
       XHTML_NAMESPACE, "td", "td", colspanAtts);
      
    }
    else { // pass the element along
      parent.startElement(namespaceURI, localName, qualifiedName, 
       atts);
    }
    
  }
  
  // Replace </rddl:resource> end-tags with the end of a table.
  public void endElement(String namespaceURI, String localName,
   String qualifiedName) throws SAXException {
     
    if (localName.equals("resource") 
        && RDDL_NAMESPACE.equals(namespaceURI)) {
      parent.endElement(XHTML_NAMESPACE, "td", "td");
      parent.endElement(XHTML_NAMESPACE, "tr", "tr");
      parent.endElement(XHTML_NAMESPACE, "table", "table");
    }
    else {
      parent.endElement(namespaceURI, localName, qualifiedName);
    }
    
  }

  // Methods that pass data along unchanged:
  public void startDocument() throws SAXException {
    parent.startDocument(); 
  }
  
  public void setDocumentLocator(Locator locator) {
    parent.setDocumentLocator(locator); 
  }
  
  public void endDocument() throws SAXException {
    parent.endDocument(); 
  }
  
  public void characters(char[] text, int start, int length)
   throws SAXException {
    parent.characters(text, start, length); 
  }
  
  public void ignorableWhitespace(char[] text, int start, 
   int length) throws SAXException {
    parent.ignorableWhitespace(text, start, length); 
  }
  
  public void processingInstruction(String target, String data)
   throws SAXException {
    parent.processingInstruction(target, data); 
  }
  
  public void startPrefixMapping(String prefix, String uri)
   throws SAXException {
    parent.startPrefixMapping(prefix, uri); 
  }
  
  public void endPrefixMapping(String prefix) 
   throws SAXException {
    parent.endPrefixMapping(prefix);
  }
    
  public void skippedEntity(String name)
   throws SAXException {
    parent.skippedEntity(name); 
  }

}
