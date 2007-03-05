import org.w3c.dom.*;


public class NBSPUtility {
  
  // Recursively descend the tree replacing all non-breaking
  // spaces with &nbsp;
  public static void addEntityReferences(Node node) {
    
    int type = node.getNodeType();
    if (type == Node.TEXT_NODE) { 
                // the only type with attributes
      Text text = (Text) node;
      String s = text.getNodeValue();
      int nbsp = s.indexOf('\u00A0'); // finds the first A0
      if (nbsp != -1) {
        Text middle = text.splitText(nbsp);
        Text end = middle.splitText(1);
        Node parent = text.getParentNode();
        Document factory = text.getOwnerDocument();
        EntityReference ref = factory.createEntityReference("nbsp");
        parent.replaceChild(ref, middle);
        addEntityReferences(end); // finds any subsequent A0s
        System.out.println("Added");
      }
    } // end if 
    
    else if (node.hasChildNodes()) {
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        Node child = children.item(i);
        addEntityReferences(child);
      } // end for
    } // end if 
    
  }  // end addEntityReferences()
  
}
