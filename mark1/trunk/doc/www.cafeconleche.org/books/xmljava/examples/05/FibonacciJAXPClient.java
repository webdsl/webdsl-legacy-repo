import java.net.*;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;


public class FibonacciJAXPClient {

  private static String DEFAULT_SERVER 
   = "http://www.elharo.com/fibonacci/XML-RPC";  
  
  public static void main(String[] args) {
      
    if (args.length <= 0) {
      System.out.println(
       "Usage: java FibonacciJAXPClient number url"
      );
      return;
    }
    
    String server = DEFAULT_SERVER;
    if (args.length >= 2) server = args[1];
      
    try {       
      // Build the request document
      DocumentBuilderFactory builderFactory 
       = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder 
       = builderFactory.newDocumentBuilder();
      Document request = builder.newDocument();

      Element methodCall = request.createElement("methodCall");
      request.appendChild(methodCall);
       
      Element methodName = request.createElement("methodName");
      Text text = request.createTextNode("calculateFibonacci");
      methodName.appendChild(text);
      methodCall.appendChild(methodName);
      
      Element params = request.createElement("params");
      methodCall.appendChild(params);
      
      Element param = request.createElement("param");
      params.appendChild(param);
      
      Element value = request.createElement("value");
      param.appendChild(value);
      
      // Had to break the naming convention here because of a 
      // conflict with the Java keyword int
      Element intElement = request.createElement("int");
      Text index = request.createTextNode(args[0]);
      intElement.appendChild(index);
      value.appendChild(intElement);  
      
      // Transmit the request document
      URL u = new URL(server);
      URLConnection uc = u.openConnection();
      HttpURLConnection connection = (HttpURLConnection) uc;
      connection.setDoOutput(true);
      connection.setDoInput(true); 
      connection.setRequestMethod("POST");
      OutputStream out = connection.getOutputStream();
      
      TransformerFactory xformFactory 
       = TransformerFactory.newInstance();
      Transformer idTransform = xformFactory.newTransformer();
      Source input = new DOMSource(request);
      Result output = new StreamResult(out);
      idTransform.transform(input, output);
      
      out.flush();
      out.close();        

      // Read the response
      InputStream in = connection.getInputStream();
      Document response = builder.parse(in);
      in.close();
      connection.disconnect();
      
      NodeList doubles = response.getElementsByTagName("double");
      Node datum = doubles.item(0);
      Text result = (Text) datum.getFirstChild();
      System.out.println(result.getNodeValue());
    }
    catch (Exception e) {
      System.err.println(e); 
    }
  
  } 

}
