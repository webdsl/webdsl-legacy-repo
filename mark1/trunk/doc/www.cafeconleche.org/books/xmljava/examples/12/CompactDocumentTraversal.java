package org.w3c.dom.traversal;

public interface DocumentTraversal {
  
  public NodeIterator createNodeIterator(Node root, 
   int whatToShow, NodeFilter filter, 
   boolean entityReferenceExpansion) throws DOMException;

  public TreeWalker createTreeWalker(Node root,
   int whatToShow, NodeFilter filter,
   boolean entityReferenceExpansion) throws DOMException;

}
