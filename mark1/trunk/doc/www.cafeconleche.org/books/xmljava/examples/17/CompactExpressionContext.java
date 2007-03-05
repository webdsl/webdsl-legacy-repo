package org.apache.xalan.extensions;

public interface ExpressionContext {

  public Node         getContextNode();
  public NodeIterator getContextNodes();
  public double       toNumber(Node n);
  public String       toString(Node n);
  public XObject      getVariableOrParam(
   org.apache.xml.utils.QName qualifiedName)
   throws javax.xml.transform.TransformerException;

}
