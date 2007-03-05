import java.net.*;
import java.io.*;
import java.math.BigInteger;


public class FibonacciClient {

  static String defaultServer 
   = "http://www.elharo.com/fibonacci/XML-RPC";
   
  public static void main(String[] args) {
      
    if (args.length <= 0) {
      System.out.println(
       "Usage: java FibonacciClient number url"
      );
      return;
    }
    
    String server = defaultServer;
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
       
      // Write the request
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
      
      // Read the response
      InputStream in = connection.getInputStream();
      BigInteger result = readFibonacciXMLRPCResponse(in);
      System.out.println(result);
        
      in.close();
      connection.disconnect();
    }
    catch (IOException e) {
      System.err.println(e); 
    }
  
  }

  private static BigInteger readFibonacciXMLRPCResponse(
   InputStream in) throws IOException, NumberFormatException, 
   StringIndexOutOfBoundsException {
    
    StringBuffer sb = new StringBuffer();
    Reader reader = new InputStreamReader(in, "UTF-8");
    int c;
    while ((c = in.read()) != -1) sb.append((char) c);
    
    String document = sb.toString();
    String startTag = "<value><double>";
    String endTag = "</double></value>";
    int start = document.indexOf(startTag) + startTag.length();
    int end = document.indexOf(endTag);
    String result = document.substring(start, end);
    return new BigInteger(result);
    
  }  

}
