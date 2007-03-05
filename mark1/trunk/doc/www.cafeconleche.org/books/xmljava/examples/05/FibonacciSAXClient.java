import java.net.*;
import java.io.*;
import java.math.BigInteger;
import org.xml.sax.*;
import org.xml.sax.helpers.*;


public class FibonacciSAXClient {

  public final static String DEFAULT_SERVER 
   = "http://www.elharo.com/fibonacci/XML-RPC";  
  
  public static void main(String[] args) {
      
    if (args.length <= 0) {
      System.out.println(
       "Usage: java FibonacciSAXClient number url"
      );
      return;
    }
    
    String server = DEFAULT_SERVER;
    if (args.length >= 2) server = args[1];
      
    try {
      // Connect to the server
      URL u = new URL(server);
      URLConnection uc = u.openConnection();
      HttpURLConnection connection = (HttpURLConnection) uc;
      connection.setDoOutput(true);
      connection.setDoInput(true); 
      connection.setRequestMethod("POST");
      OutputStream out = connection.getOutputStream();
      Writer wout = new OutputStreamWriter(out);
      
      // Transmit the request XML document
      wout.write("<?xml version=\"1.0\"?>\r\n");  
      wout.write("<methodCall>\r\n"); 
      wout.write(
       "  <methodName>calculateFibonacci</methodName>\r\n");
      wout.write("  <params>\r\n"); 
      wout.write("    <param>\r\n"); 
      wout.write("      <value><int>" + args[0] 
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
      // There's a name conflict with java.net.ContentHandler
      // so we have to use the fully package qualified name.
      org.xml.sax.ContentHandler handler 
       = new FibonacciHandler();
      parser.setContentHandler(handler);
    
      InputStream in = connection.getInputStream();
      InputSource source = new InputSource(in);
      parser.parse(source);
      System.out.println();

      in.close();
      connection.disconnect();
    }
    catch (Exception e) {
      System.err.println(e); 
    }
  
  } 

}
