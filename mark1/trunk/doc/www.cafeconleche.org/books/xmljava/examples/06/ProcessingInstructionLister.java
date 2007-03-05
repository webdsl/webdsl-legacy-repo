import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;


public class ProcessingInstructionLister extends DefaultHandler {

  
  public void processingInstruction(String target, String data) {
     
    System.out.println("Processing Instruction:");
    System.out.println("  target: " + target);
    System.out.println("  data:   " + data);
    System.out.println();
    
  }
  
  
  public static void main(String[] args) {
      
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader(
        "org.apache.xerces.parsers.SAXParser"
      );
      ContentHandler handler = new ProcessingInstructionLister();
      parser.setContentHandler(handler);
      for (int i = 0; i < args.length; i++) {  
        parser.parse(args[i]);
      }
    }
    catch (Exception e) {
      System.err.println(e);
    }
  
  }    
  
}
