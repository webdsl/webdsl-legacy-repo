import org.w3c.dom.*;


public class Restructurer {

  // Since this method only operates on its argument and does
  // not interact with any fields in the class, it's 
  // plausibly made static.
  public static void processNode(Node current) 
   throws DOMException {
    
    // I need to store a reference to the current node's next
    // sibling before we delete the node from the tree, in which
    // case it no longer has a sibling
    Node nextSibling = current.getNextSibling();
    
    int nodeType = current.getNodeType();
    if (nodeType == Node.COMMENT_NODE 
     || nodeType == Node.PROCESSING_INSTRUCTION_NODE) {
       
      Node document = current.getOwnerDocument();
      // Find the root element by looping through the children of 
      // the document until we find the only one that's an 
      // element node. There's a quicker way to do this once we 
      // learn more about the Document class in the next chapter.
      Node root = document.getFirstChild();
      while (!(root.getNodeType() == Node.ELEMENT_NODE )) {
        root = root.getNextSibling();
      }

      Node parent = current.getParentNode();
      parent.removeChild(current);
      if (nodeType == Node.COMMENT_NODE) {
        document.appendChild(current);
      }
      else if (nodeType == Node.PROCESSING_INSTRUCTION_NODE) {
        document.insertBefore(current, root);
      }
      
    }
    else if (current.hasChildNodes()) {
      Node firstChild = current.getFirstChild();
      processNode(firstChild);
    }
    
    if (nextSibling != null) {
      processNode(nextSibling);
    }
    
  }

}


