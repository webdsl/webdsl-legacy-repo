import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;


public class WallclockDriver {

  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java WallclockDriver URL");
      return;
    }
    String document = args[0];
    
    try {
      XMLFilter filter = new WallclockFilter();
      filter.setParent(XMLReaderFactory.createXMLReader());
      filter.parse(document);
      Long parseTime = (Long) filter.getProperty(
       "http://cafeconleche.org/properties/wallclock/");
       double seconds = parseTime.longValue()/1000.0;
      System.out.println("Parsing " + document + " took "
       + seconds + " seconds on average.");   
    }
    catch (SAXException e) {
      e.printStackTrace();
      System.out.println(e);
    }
    catch (IOException e) { 
        e.printStackTrace();
     System.out.println(
       "Due to an IOException, the parser could not check " 
       + args[0]
      ); 
    }
    
  }
  
}
