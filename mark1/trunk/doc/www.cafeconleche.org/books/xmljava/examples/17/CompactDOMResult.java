package javax.xml.transform.dom;

public class DOMResult implements Result {

  public static final String FEATURE =
  "http://javax.xml.transform.dom.DOMResult/feature";

  public DOMResult();
  public DOMResult(Node node);
  public DOMResult(Node node, String systemID);
  
  public void setNode(Node node);
  public Node getNode();
  public void setSystemId(String systemId);
  public String getSystemId();
  
}
