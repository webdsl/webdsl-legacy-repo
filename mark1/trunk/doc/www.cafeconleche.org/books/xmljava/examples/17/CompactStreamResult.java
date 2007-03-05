package javax.xml.transform.stream;

public class StreamResult implements Result

  public static final String FEATURE =
   "http://javax.xml.transform.stream.StreamResult/feature";

  public StreamResult() {}
  public StreamResult(OutputStream outputStream);
  public StreamResult(Writer writer);
  public StreamResult(String systemID);
  public StreamResult(File f);
  
  public void         setOutputStream(OutputStream outputStream);
  public OutputStream getOutputStream();
  public void         setWriter(Writer writer);
  public Writer       getWriter();
  public void         setSystemId(String systemID);
  public void         setSystemId(File f);
  public String       getSystemId();
  
}
