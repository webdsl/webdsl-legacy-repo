import java.net.*;
import java.io.*;
import org.jdom.*;
import org.jdom.output.XMLOutputter;
import org.jdom.input.SAXBuilder;


public class FibonacciJDOMClient {

  public final static String DEFAULT_SERVER 
   = "http://www.elharo.com/fibonacci/XML-RPC";  
  
  public static void main(String[] args) {
      
    if (args.length <= 0) {
      System.out.println(
       "Usage: java FibonacciJDOMClient number url"
      );
      return;
    }
    
    String server = DEFAULT_SERVER;
    if (args.length >= 2) server = args[1];
      
    try {
      // Build the request document
      Element methodCall = new Element("methodCall");
      Element methodName = new Element("methodName");
      methodName.setText("calculateFibonacci");
      methodCall.addContent(methodName);
      Element params = new Element("params");
      methodCall.addContent(params);
      Element param = new Element("param");
      params.addContent(param);
      Element value = new Element("value");
      param.addContent(value);
      // Had to break the naming convention here because of a 
      // conflict with the Java keyword int
      Element intElement = new Element("int");
      intElement.setText(args[0]);
      value.addContent(intElement);  
      Document request = new Document(methodCall);
      
      // Transmit the request document
      URL u = new URL(server);
      URLConnection uc = u.openConnection();
      HttpURLConnection connection = (HttpURLConnection) uc;
      connection.setDoOutput(true);
      connection.setDoInput(true); 
      connection.setRequestMethod("POST");
      OutputStream out = connection.getOutputStream();
      
      XMLOutputter serializer = new XMLOutputter();
      serializer.output(request, out);
      
      out.flush();
      out.close();        

      // Read the response
      InputStream in = connection.getInputStream();
      SAXBuilder parser = new SAXBuilder();
      Document response = parser.build(in);
      in.close();
      connection.disconnect();
      
      // Walk down the tree
      String result = response.getRootElement()
                       .getChild("params")
                       .getChild("param")
                       .getChild("value")
                       .getChild("double")
                       .getText();
      System.out.println(result);
    }
    catch (Exception e) {
      System.err.println(e); 
    }
  
  } 

}
