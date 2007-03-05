import org.xmlpull.v1.*;
import java.io.*;
import java.net.*;


public class FibonacciPullClient {

  public final static String DEFAULT_SERVER 
   = "http://www.elharo.com/fibonacci/XML-RPC";  
  
  public static void main(String[] args) {
      
    if (args.length <= 0) {
      System.out.println(
       "Usage: java FibonacciPullClient number url"
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

      // Load the parser
      XmlPullParserFactory factory 
       = XmlPullParserFactory.newInstance();
      XmlPullParser parser = factory.newPullParser();
      
      // Read the response XML document
      InputStream in = connection.getInputStream();
      parser.setInput(in, null);
      //                  ^^^^
      // Encoding is not known; the parser should guess it based
      // on the content of the stream.
      
      int event;
      while ( 
       (event = parser.next()) != XmlPullParser.END_DOCUMENT) {
        if( event ==  XmlPullParser.START_TAG) {
          if ( "double".equals(parser.getName()) ) {
            String value = parser.nextText();
            System.out.println(value);
          } // end if
        } // end if 
      } // end while
      
      in.close();
      connection.disconnect();

    }
    catch (Exception e) {
      System.err.println(e); 
    }
  
  } 

}
