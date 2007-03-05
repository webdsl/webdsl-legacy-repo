import java.net.*;
import java.io.*;
import java.math.BigInteger;
import org.xml.sax.*;
import org.xml.sax.helpers.*;


public class NewFibonacciClient {

  public final static String DEFAULT_SERVER 
   = "http://www.elharo.com/fibonacci/XML-RPC";  

  public static BigInteger calculateFibonacci(int index, 
   String server) throws IOException, SAXException {

      // Connect to the the server
      URL u = new URL(server);
      URLConnection uc = u.openConnection();
      HttpURLConnection connection = (HttpURLConnection) uc;
      connection.setDoOutput(true);
      connection.setDoInput(true); 
      connection.setRequestMethod("POST");
      OutputStream out = connection.getOutputStream();
      Writer wout = new OutputStreamWriter(out, "UTF-8");
      
      // Transmit the request XML document
      wout.write("<?xml version=\"1.0\"?>\r\n");  
      wout.write("<methodCall>\r\n"); 
      wout.write(
       "  <methodName>calculateFibonacci</methodName>\r\n");
      wout.write("  <params>\r\n"); 
      wout.write("    <param>\r\n"); 
      wout.write("      <value><int>" + index 
       + "</int></value>\r\n"); 
      wout.write("    </param>\r\n"); 
      wout.write("  </params>\r\n"); 
      wout.write("</methodCall>\r\n"); 
      
      wout.flush();
      wout.close();      

       // Read the response XML document
      XMLReader parser = XMLReaderFactory.createXMLReader(
        "org.apache.xerces.parsers.SAXParser"
      );
      FibonacciHandler handler = new FibonacciHandler();
      parser.setContentHandler(handler);
    
      InputStream in = connection.getInputStream();
      InputSource source = new InputSource(in);
      parser.parse(source);

      in.close();
      connection.disconnect();
      return handler.result;    
    
  }
   
  static class FibonacciHandler extends DefaultHandler {

    StringBuffer buffer = null;
    BigInteger result = null;
  
    public void startElement(String namespaceURI, 
     String localName, String qualifiedName, Attributes atts) {
    
      if (qualifiedName.equals("double")) {
        buffer = new StringBuffer();
      }
      
    }

    public void endElement(String namespaceURI, String localName,
     String qualifiedName) {
    
      if (qualifiedName.equals("double")) {
        String accumulatedText = buffer.toString();
        result = new BigInteger(accumulatedText);
        buffer = null;
      }
    
    }

    public void characters(char[] text, int start, int length)
     throws SAXException {

      if (buffer != null) {
        buffer.append(text, start, length); 
      }
   
    }
    
  }
    
  public static void main(String[] args) {
      
    int index;
    try {
      index = Integer.parseInt(args[0]);
    }
    catch (Exception e) {
      System.out.println(
       "Usage: java NewFibonacciClient number url"
      );
      return;
    }

    String server = DEFAULT_SERVER;
    if (args.length >= 2) server = args[1];
    
    try {
      BigInteger result = calculateFibonacci(index, server);
      System.out.println(result);
    }
    catch (Exception e) {
      e.printStackTrace(); 
    }
  
  } 

}
