package org.xml.sax;

public class InputSource {
    
  public InputSource()
  public InputSource(String systemID)
  public InputSource(InputStream byteStream)
  public InputSource(Reader characterStream)
  
  public void        setPublicId(String publicID)
  public String      getPublicId()
  public void        setSystemId(String systemID)
  public String      getSystemId()
  public void        setByteStream(InputStream byteStream)
  public InputStream getByteStream()
  public void        setEncoding(String encoding)
  public String      getEncoding()
  public void        setCharacterStream(Reader characterStream)
  public Reader      getCharacterStream()
    
}
