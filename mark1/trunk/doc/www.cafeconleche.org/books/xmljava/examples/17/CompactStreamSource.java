package javax.xml.transform.stream;

public class StreamSource implements Source {

  public static final String FEATURE =
   "http://javax.xml.transform.stream.StreamSource/feature";

  public StreamSource();
  public StreamSource(InputStream inputStream);
  public StreamSource(InputStream inputStream, String systemID);
  public StreamSource(Reader reader);
  public StreamSource(Reader reader, String systemID);
  public StreamSource(String systemID);
  public StreamSource(File f);
  
  public void        setInputStream(InputStream inputStream);
  public InputStream getInputStream();
  public void        setReader(Reader reader);
  public Reader      getReader();
  public void        setPublicId(String publicID);
  public String      getPublicId();
  public void        setSystemId(String systemID);
  public String      getSystemId();
  public void        setSystemId(File f);
  
}
