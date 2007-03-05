import org.w3c.dom.*;


public class CDATAUtility {

  // Recursively descend the tree converting all CDATA sections
  // to text nodes and merging them with adjacent text nodes.
  public static void superNormalize(Node parent) {
    
    // We'll need this to create new Text objects
    Document factory = parent.getOwnerDocument();
      
    Node current = parent.getFirstChild();
    while (current != null) {
      
      int type = current.getNodeType();
      if (type == Node.CDATA_SECTION_NODE) {
        // Convert CDATA section to a text node
        CDATASection cdata = (CDATASection) current;
        String data = cdata.getData();
        Text newNode = factory.createTextNode(data);
        parent.replaceChild(newNode, cdata);
        current = newNode;
      }
      
      // Recheck in case we changed type above
      type = current.getNodeType();
      if (type == Node.TEXT_NODE) {
        // If previous node is a text node, then append this 
        // node's data to that node, and delete this node
        Node previous = current.getPreviousSibling();
        if (previous != null) {
          int previousType = previous.getNodeType(); 
          if (previousType == Node.TEXT_NODE) {
            Text previousText = (Text) previous;
            Text currentText = (Text) current;
            String data = currentText.getData();
            previousText.appendData(data);
            parent.removeChild(current);
            current = previous;
          }
        }
      } // end if 
      else { // recurse 
        superNormalize(current);
      }
      
      // increment node
      current = current.getNextSibling();
      
    } // end while
    
  }  // end superNormalize()
  
}
