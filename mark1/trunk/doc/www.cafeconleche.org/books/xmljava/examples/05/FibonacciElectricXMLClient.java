import java.net.*;
import java.io.*;
import electric.xml.*;


public class FibonacciElectricXMLClient {

  private static String defaultServer 
   = "http://www.elharo.com/fibonacci/XML-RPC";  
  
  public static void main(String[] args) {
      
    if (args.length <= 0) {
      System.out.println(
       "Usage: java FibonacciElectricXMLClient number url"
      );
      return;
    }
    
    String server = defaultServer;
    if (args.length >= 2) server = args[1];
      
    try {
      // Build request document
      Document request = new Document();
      request.setRoot("methodCall");
      Element methodCall = request.getRoot();
      Element methodName = methodCall.addElement("methodName");
      methodName.setText("calculateFibonacci");
      Element params = methodCall.addElement("params");
      Element param = params.addElement("param");
      Element value = param.addElement("value");
      // Had to break the naming convention here because of a 
      // conflict with the Java keyword int
      Element intElement = value.addElement("int");
      intElement.setText(args[0]);
      
      // Transmit the request documentf
      URL u = new URL(server);
      URLConnection uc = u.openConnection();
      HttpURLConnection connection = (HttpURLConnection) uc;
      connection.setDoOutput(true);
      connection.setDoInput(true); 
      connection.setRequestMethod("POST");
      OutputStream out = connection.getOutputStream();
      request.write(out);        
      out.flush();
      out.close();        

      // Read the response
      InputStream in = connection.getInputStream();
      Document response = new Document(in);
      in.close();
      connection.disconnect();
      
      // Walk down the tree
      String result = response.getRoot()
                       .getElement("params")
                       .getElement("param")
                       .getElement("value")
                       .getElement("double")
                       .getTextString();
      System.out.println(result);
      
    }
    catch (Exception e) {
      System.err.println(e); 
    }
  
  } 

}
