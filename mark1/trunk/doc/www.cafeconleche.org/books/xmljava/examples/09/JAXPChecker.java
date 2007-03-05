import javax.xml.parsers.*; // JAXP
import org.xml.sax.SAXException;
import java.io.IOException;


public class JAXPChecker {

  public static void main(String[] args) {
     
    if (args.length <= 0) {
      System.out.println("Usage: java JAXPChecker URL");
      return;
    }
    String document = args[0];
    
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();
      parser.parse(document); 
      System.out.println(document + " is well-formed.");
    }
    catch (SAXException e) {
      System.out.println(document + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not check " 
       + document
      ); 
    }
    catch (FactoryConfigurationError e) { 
      // JAXP suffers from excessive brain-damage caused by 
      // intellectual in-breeding at Sun. (Basically the Sun 
      // engineers spend way too much time talking to each other
      // and not nearly enough time talking to people outside 
      // Sun.) Fortunately, you can happily ignore most of the 
      // JAXP brain damage and not be any the poorer for it.
      
      // This, however, is one of the few problems you can't 
      // avoid if you're going to use JAXP at all. 
      // DocumentBuilderFactory.newInstance() should throw a 
      // ClassNotFoundException if it can't locate the factory
      // class. However, what it does throw is an Error,
      // specifically a FactoryConfigurationError. Very few 
      // programs are prepared to respond to errors as opposed
      // to exceptions. You should catch this error in your 
      // JAXP programs as quickly as possible even though the
      // compiler won't require you to, and you should 
      // never rethrow it or otherwise let it escape from the 
      // method that produced it. 
      System.out.println("Could not locate a factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println("Could not locate a JAXP parser"); 
    }
   
  }

}
