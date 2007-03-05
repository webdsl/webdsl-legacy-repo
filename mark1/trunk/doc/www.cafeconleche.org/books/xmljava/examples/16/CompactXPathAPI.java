package org.apache.xpath;

public class XPathAPI {

  public static Node selectSingleNode(Node context, String xpath)
   throws TransformerException;
  public static Node selectSingleNode(Node context, String xpath, 
   Node namespaceContextNode) throws TransformerException;
  public static NodeIterator selectNodeIterator(Node context, 
   String xpath) throws TransformerException;
  public static NodeIterator selectNodeIterator(Node context, 
   String xpath, Node namespaceContextNode) 
   throws TransformerException;
  public static NodeList selectNodeList(Node context, 
   String xpath) throws TransformerException;
  public static NodeList selectNodeList(Node context, 
   String xpath, Node namespaceContextNode)
   throws TransformerException;
  public static XObject eval(Node context, String xpath)
   throws TransformerException;
  public static XObject eval(Node context, String xpath, 
   Node namespaceContextNode) throws TransformerException;
  public static XObject eval(Node context, String xpath, 
   PrefixResolver prefixResolver) throws TransformerException;
   
}
