package javax.xml.transform.sax;

public class SAXSource implements Source {

  public static final String FEATURE =
   "http://javax.xml.transform.sax.SAXSource/feature";

  public SAXSource();
  public SAXSource(XMLReader reader, InputSource inputSource);
  public SAXSource(InputSource inputSource);
  
  public void        setXMLReader(XMLReader reader);
  public XMLReader   getXMLReader();
  public void        setInputSource(InputSource inputSource);
  public InputSource getInputSource();
  public void        setSystemId(String systemID);
  public String      getSystemId();
  
  public static InputSource sourceToInputSource(Source source);
  
}
