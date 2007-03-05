package javax.xml.transform.sax;

public class SAXResult implements Result

  public static final String FEATURE =
   "http://javax.xml.transform.sax.SAXResult/feature";

  public SAXResult();
  public SAXResult(ContentHandler handler);
  
  public void           setHandler(ContentHandler handler);
  public ContentHandler getHandler();
  public void           setLexicalHandler(LexicalHandler handler);
  public LexicalHandler getLexicalHandler();
  public void           setSystemId(String systemId);
  public String         getSystemId();
  
}
