import org.w3c.dom.*;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.ls.DOMWriterFilter;


public class XHTMLFilter implements DOMWriterFilter {
  
  public final static String XHTML_NAMESPACE 
   = "http://www.w3.org/1999/xhtml";

  // This filter only operates on elements. Everything else
  // will be output without passing through the filter. However
  // descendants of non-XHTML elements will not be output
  // because their ancestor elements have been rejected.
  // Note that this means we don't fully handle nested XHTML;
  // e.g. XHTML contains SVG which contains XHTML.
  // XHTML inside SVG will not be output.
  public int getWhatToShow() {
    return NodeFilter.SHOW_ELEMENT;    
  }
  
  
  public short acceptNode(Node node) {
     
    // Is this necessary or does getWhatToShow() handle this????
    // I've requested clarification from the DOM working group.
    int type = node.getNodeType();
    if (type != Node.ELEMENT_NODE) {
      return NodeFilter.FILTER_ACCEPT;
    }   

    String namespace = node.getNamespaceURI();
    if (XHTML_NAMESPACE.equals(namespace)) {
      return NodeFilter.FILTER_ACCEPT;
    }
    else {
     return NodeFilter.FILTER_SKIP; 
    }

  }

}
