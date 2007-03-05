package org.w3c.dom.xpath;

public interface XPathExpression {

  public XPathResult evaluate(Node contextNode, short type, 
   XPathResult result) throws XPathException, DOMException;

}
