package javax.xml.transform.dom;

public class DOMSource implements Source {

  public static final String FEATURE =
    "http://javax.xml.transform.dom.DOMSource/feature";

  public DOMSource() {}
  public DOMSource(Node node);
  public DOMSource(Node node, String systemID);

  public void    setNode(Node node);
  public Node   getNode();
  public void    setSystemId(String baseID);
  public String getSystemId();

}
