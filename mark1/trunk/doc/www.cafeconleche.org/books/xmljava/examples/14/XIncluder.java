import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.SAXOutputter;
import java.io.IOException;
import gnu.xml.pipeline.*;
import org.xml.sax.SAXException;


public class XIncluder {

  public static void main(String[] args) {
  
    if (args.length == 0) {
      System.out.println("Usage: java XIncluder URL"); 
      return;
    } 
      
    SAXBuilder builder = new SAXBuilder(
     "gnu.xml.aelfred2.XmlReader");

    // command line should offer URIs or file names
    try {
      Document doc = builder.build(args[0]);
      XIncludeFilter filter = new XIncludeFilter(
        new TextConsumer(System.out)
      );
      SAXOutputter outputter = new SAXOutputter(filter);
      outputter.setContentHandler(filter);
      outputter.setDTDHandler(filter);
      outputter.setLexicalHandler(filter);
      outputter.output(doc);
    }
    // indicates a well-formedness error
    catch (JDOMException e) { 
      System.out.println(args[0] + " is not well-formed.");
      System.out.println(e.getMessage());
    }  
    catch (SAXException e) { 
      System.out.println(e.getMessage());
    }  
    catch (IOException e) { 
      System.out.println("Could not merge " + args[0]);
      System.out.println(" because " + e.getMessage());
    }  
  
  }

}
