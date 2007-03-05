import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.*;


public class XLinkFilter implements NodeFilter {

  public static String XLINK_NAMESPACE 
   = "http://www.w3.org/1999/xlink";
  
  public short acceptNode(Node node) {
     
    Element candidate = (Element) node;
    String type 
     = candidate.getAttributeNS(XLINK_NAMESPACE, "type");
    if (type.equals("simple")) return FILTER_ACCEPT;
    return FILTER_SKIP;
     
  }

}
