package org.w3c.dom;

public interface Node {

  // Node type constants
  public static final short ELEMENT_NODE                = 1;
  public static final short ATTRIBUTE_NODE              = 2;
  public static final short TEXT_NODE                   = 3;
  public static final short CDATA_SECTION_NODE          = 4;
  public static final short ENTITY_REFERENCE_NODE       = 5;
  public static final short ENTITY_NODE                 = 6;
  public static final short PROCESSING_INSTRUCTION_NODE = 7;
  public static final short COMMENT_NODE                = 8;
  public static final short DOCUMENT_NODE               = 9;
  public static final short DOCUMENT_TYPE_NODE          = 10;
  public static final short DOCUMENT_FRAGMENT_NODE      = 11;
  public static final short NOTATION_NODE               = 12;

  // Node properties
  public String   getNodeName();
  public String   getNodeValue() throws DOMException;
  public void     setNodeValue(String nodeValue) 
   throws DOMException;
  public short    getNodeType();
  public String   getNamespaceURI();
  public String   getPrefix();
  public void     setPrefix(String prefix) throws DOMException;
  public String   getLocalName();

  // Navigation methods 
  public Node         getParentNode();
  public boolean      hasChildNodes();
  public NodeList     getChildNodes();
  public Node         getFirstChild();
  public Node         getLastChild();
  public Node         getPreviousSibling();
  public Node         getNextSibling();
  public Document     getOwnerDocument();
  public boolean      hasAttributes();
  public NamedNodeMap getAttributes();

  // Manipulator methods 
  public Node insertBefore(Node newChild, Node refChild)
   throws DOMException;
  public Node replaceChild(Node newChild,  Node oldChild)
   throws DOMException;
  public Node removeChild(Node oldChild) throws DOMException;
  public Node appendChild(Node newChild) throws DOMException;

  // Utility methods
  public Node cloneNode(boolean deep);
  public void normalize();
  public boolean isSupported(String feature, String version);

}
