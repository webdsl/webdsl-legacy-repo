import org.xml.sax.*;
import java.io.*;
import java.util.Date;
import java.net.URL;


public class WallclockFilter implements XMLFilter {

  private XMLReader parent;
  private Long wallclock = null;
  
  public Object getProperty(String name) 
   throws SAXNotRecognizedException, SAXNotSupportedException {
     
    if ("http://cafeconleche.org/properties/wallclock/"
     .equals(name)) {
      if (wallclock != null) {
        return wallclock;
      }
      else {
        throw 
         new SAXNotSupportedException("Timing not available");
      }
    }
    return parent.getProperty(name);
    
  }

  public void setProperty(String name, Object value)
   throws SAXNotRecognizedException, SAXNotSupportedException {
     
    if ("http://cafeconleche.org/properties/wallclock/"
     .equals(name)) {
      throw new SAXNotSupportedException(
       "Wallclock property is read-only");
    }
    parent.setProperty(name, value);
    
  }

  public void setParent(XMLReader parent) {
    this.parent = parent;
  }
  
  public XMLReader getParent() {
    return this.parent; 
  }

  public void parse(InputSource input)
   throws SAXException, IOException {
     
    //Reset the time
    this.wallclock = null;
     
    // Cache the document 
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Reader charStream = input.getCharacterStream();
    InputStream byteStream = input.getByteStream();
    
    String encoding = null; // I will only set this variable if 
                            // we have a reader because in this
                            // case we know the encoding is UTF-8
                            // regardless of what the encoding
                            // declaration says
    if (charStream != null) {
      OutputStreamWriter filter 
       = new OutputStreamWriter(out, "UTF-8");
      int c;
      while ((c = charStream.read()) != -1) filter.write(c);
      encoding = "UTF-8";
    }
    else if (byteStream != null) {
      int c;
      while ((c = byteStream.read()) != -1) out.write(c);
    }
    else {
      URL u = new URL(input.getSystemId());
      InputStream in = u.openStream();
      int c;
      while ((c = in.read()) != -1) out.write(c);
    }
    out.flush();
    out.close();
    byte[] cache = out.toByteArray();
     
    InputSource is = new InputSource(); 
    if (encoding != null) is.setEncoding(encoding);
     
    // Warm up the JIT
    for (int i=0; i < 10; i++) {
      InputStream in = new ByteArrayInputStream(cache);
      is.setByteStream(in); 
      parent.parse(is);
    }
    System.gc();
    
    // Parse 1000 times 
    Date start = new Date();
    for (int i=0; i < 1000; i++) {
      InputStream in = new ByteArrayInputStream(cache);
      is.setByteStream(in);
      parent.parse(is); 
    }
    Date finish = new Date();
    long totalTime = finish.getTime() - start.getTime();
    
    // Average the time
    this.wallclock = new Long(totalTime/1000);
    
  }
  
  public void parse(String systemID) 
   throws SAXException, IOException {
    this.parse(new InputSource(systemID));
  }
 
  // Methods that delegate to the parent XMLReader
  public boolean getFeature(String name)
   throws SAXNotRecognizedException, SAXNotSupportedException {
    return parent.getFeature(name);
  } 

  public void setFeature(String name, boolean value) 
   throws SAXNotRecognizedException, SAXNotSupportedException { 
    parent.setFeature(name, value);
  }

  public void setEntityResolver(EntityResolver resolver) {
    parent.setEntityResolver(resolver);
  }
  
  public EntityResolver getEntityResolver() {
    return parent.getEntityResolver();
  }
  
  public void setDTDHandler(DTDHandler handler) {
    parent.setDTDHandler(handler);
  }
  
  public DTDHandler getDTDHandler() {
    return parent.getDTDHandler();
  }

  public void setContentHandler(ContentHandler handler) {
    parent.setContentHandler(handler);  
  }
  
  public ContentHandler getContentHandler() {
    return parent.getContentHandler();
  }

  public void setErrorHandler(ErrorHandler handler) {
    parent.setErrorHandler(handler);
  }
  
  public ErrorHandler getErrorHandler() {
    return parent.getErrorHandler();
  }  
 
}
