package org.w3c.dom.xpath;

public interface XPathEvaluator {
  
  public XPathResult evaluate(String expression, 
   Node contextNode, XPathNSResolver resolver, short type,
   XPathResult result) throws XPathException, DOMException;

  public XPathExpression createExpression(String expression, 
   XPathNSResolver resolver) throws XPathException, DOMException;
  public XPathNSResolver createNSResolver(Node nodeResolver);

}
