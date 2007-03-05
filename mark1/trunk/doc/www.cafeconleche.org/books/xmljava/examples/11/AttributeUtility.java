import org.w3c.dom.*;


public class AttributeUtility {

  // Recursively descend the tree replacing all unspecified
  // attributes with specified attributes
  public static void specifyAttributes(Node node) {
    
    int type = node.getNodeType();
    if (type == Node.ELEMENT_NODE) { 
                // the only type with attributes
      Element element = (Element) node;
      NamedNodeMap attributes = element.getAttributes();
      Document factory = node.getOwnerDocument();
      for (int i = 0; i < attributes.getLength(); i++) {
        Attr attribute = (Attr) attributes.item(i);
        if (!attribute.getSpecified()) {
          // We can't change the specified property of an
          // attribute in DOM2. However, attributes are specified
          // by default, so if we delete the old attribute and 
          // add a new one with the same name and value, or 
          // change the attribute's value (even  to the same 
          // thing) the effect is what we're looking for.
          String name = attribute.getName();
          String value = attribute.getValue();
          Attr specifiedAttribute = factory.createAttribute(name);
          specifiedAttribute.setValue(value);
          element.setAttributeNode(specifiedAttribute);
          // This replaces the old attribute with the same name.
        }
      } // end for   
    } // end if 
    
    if (node.hasChildNodes()) {
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        Node child = children.item(i);
        specifyAttributes(child);
      } // end for
    } // end if 
    
  }  // end specifyAttributes()
  
}
