package org.w3c.dom;

public interface NamedNodeMap {

  // for iterating through the map as a list
  public Node item(int index);
  public int  getLength();

  // For working with particular items in the list
  public Node getNamedItem(String name);
  public Node setNamedItem(Node arg) throws DOMException;
  public Node removeNamedItem(String name)
   throws DOMException;
  public Node getNamedItemNS(String namespaceURI, 
   String localName);
  public Node setNamedItemNS(Node arg) throws DOMException;
  public Node removeNamedItemNS(String namespaceURI, 
   String localName) throws DOMException;

}
