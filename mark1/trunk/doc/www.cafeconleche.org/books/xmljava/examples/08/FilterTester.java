import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.*;
import com.megginson.sax.XMLWriter;


public class FilterTester {

  public static void main(String[] args) {

    if (args.length < 2) {
      System.out.println(
       "Usage: java FilterTester URL FilterClass");
      return;
    }
    String document = args[0];
    String filterClass = args[1];
    
    try {
      XMLFilter filter 
       = (XMLFilter) Class.forName(filterClass).newInstance();
      filter.setParent(XMLReaderFactory.createXMLReader());
      filter.setContentHandler(
       new XMLWriter(new OutputStreamWriter(System.out))
      );
      filter.parse(document);
    }
    catch (SAXException e) {
      e.printStackTrace();
      System.out.println(e);
    }
    catch (IOException e) { 
        e.printStackTrace();
     System.out.println(
       "Due to an IOException, the parser could not read " 
       + args[0]
      ); 
    }
    catch (ClassCastException e) {
      System.out.println(filterClass 
       + " does not implement org.xml.sax.XMLFilter");
    }
    catch (ClassNotFoundException e) {
      System.out.println(filterClass 
       + " cannot be found in the CLASSPATH");
    }
    catch (InstantiationException e) {
      System.out.println(filterClass 
       + " does not have a no-args constructor");
    }
    catch (Exception e) {
      System.err.println(e);
    }
    
  }
  
}
