import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.*;


public class ExampleFilter implements NodeFilter {

  public short acceptNode(Node node) {
     
    Element candidate = (Element) node;
    String name = candidate.getNodeName(); 
    if (name.equals("example")) return FILTER_ACCEPT;
    else if (name.equals("chapter")) return FILTER_ACCEPT;
    else if (name.equals("book")) return FILTER_ACCEPT;
    else if (name.equals("title")) {
      // Is this the title of an example, in which case we accept
      // it, or the title of something else, in which case we
      // reject it?
      Node parent = node.getParentNode();
      if ("example".equals(parent.getNodeName())) {
        return FILTER_ACCEPT;
      }
    }
    return FILTER_SKIP;
     
  }

}
