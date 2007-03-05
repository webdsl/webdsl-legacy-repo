import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;


public class ExampleExtractor {
  
  public static void extract(Document doc) throws IOException {

    NodeList chapters = doc.getElementsByTagName("chapter");
    
    for (int i = 0; i < chapters.getLength(); i++) {
      
      Element chapter = (Element) chapters.item(i);
      NodeList examples = chapter.getElementsByTagName("example");
      
      for (int j = 0; j < examples.getLength(); j++) {
        
        Element example = (Element) examples.item(j);
        String fileName = example.getAttribute("id");
        // All examples should have id attributes but it's safer
        // not to assume that
        if (fileName == null) {
          throw 
           new IllegalArgumentException("Missing id on example"); 
        }
        NodeList programlistings 
         = example.getElementsByTagName("programlisting");
        // Each example is supposed to contain exactly one 
        // programlisting, but we should verify that
        if (programlistings.getLength() != 1) {
          throw new 
           IllegalArgumentException("Missing programlisting"); 
        }
        Element programlisting = (Element) programlistings.item(0);
        
        // Extract text content; this is a little tricky because
        // these often contain CDATA sections and entity
        // references which can be represented as separate nodes
        // so we can't just ask for the first text node child of
        // each program listing.
        String code = getText(programlisting);
        
        // write code into a file
        File dir = new File("examples2/" + i);
        dir.mkdirs();
        File file = new File(dir, fileName);
        System.out.println(file);
        FileOutputStream fout = new FileOutputStream(file);
        Writer out = new OutputStreamWriter(fout, "UTF-8");
        // Buffering almost always helps performance a lot
        out = new BufferedWriter(out);
        out.write(code);
        // Be sure to remember to flush and close your streams
        out.flush();
        out.close();
        
      } // end examples loop
      
    } // end chapters loop

  }
  
  public static String getText(Node node) {
    
    // We need to retrieve the text from elements, entity
    // references, CDATA sections, and text nodes; but not
    // comments or processing instructions
    int type = node.getNodeType();
    if (type == Node.COMMENT_NODE 
     || type == Node.PROCESSING_INSTRUCTION_NODE) {
       return "";
    } 
    
    StringBuffer text = new StringBuffer();

    String value = node.getNodeValue();
    if (value != null) text.append(value);
    if (node.hasChildNodes()) {
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        Node child = children.item(i);  
        text.append(getText(child));
      }
    }
    
    return text.toString();
    
  }
  
  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java ExampleExtractor URL");
      return;
    }
    String url = args[0];
    
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
      factory.setValidating(true);
      
      DocumentBuilder parser = factory.newDocumentBuilder();
      parser.setErrorHandler(new ValidityRequired());
      
      // Read the document
     Document document = parser.parse(url); 
     
     // Extract the examples
     extract(document);

    }
    catch (SAXException e) {
      System.out.println(e);
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not read " + url
      ); 
      System.out.println(e);
    }
    catch (FactoryConfigurationError e) { 
      System.out.println("Could not locate a factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println("Could not locate a JAXP parser"); 
    } 
     
  } // end main
  
}

// Make validity errors fatal
class ValidityRequired implements ErrorHandler {

  public void warning(SAXParseException e)
    throws SAXException {
    // ignore warnings  
  }
  
  public void error(SAXParseException e)
   throws SAXException {
    // Mostly validity errors. Make them fatal.
    throw e;
  }
  
  public void fatalError(SAXParseException e)
   throws SAXException {
    throw e;
  }
  
}
