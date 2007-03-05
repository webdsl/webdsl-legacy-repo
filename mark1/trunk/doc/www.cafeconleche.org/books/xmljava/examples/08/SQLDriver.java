import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.*;
import com.megginson.sax.DataWriter;
import java.sql.SQLException;


public class SQLDriver {

  public static void main(String[] args) {
    
    if (args.length < 2) {
      System.out.println(
       "Usage: java SQLDriver URL query driverClass");
      return;
    }
    String url = args[0];
    String query = args[1];
    String driverClass = "org.gjt.mm.mysql.Driver"; // MySQL
    if (args.length >= 3) driverClass = args[2];
    
    try {
      // Load JDBC driver
      Class.forName(driverClass).newInstance();
      // Technically, the newInstance() call isn't needed, 
      // but the MM.MySQL documentation suggests this to 
      // "work around some broken JVMs"
    
      XMLFilter filter = new DatabaseFilter(url);
      DataWriter out = new DataWriter();
      out.forceNSDecl(
       "http://www.w3.org/2001/XMLSchema-instance", "xsi");
      out.forceNSDecl("http://www.w3.org/2001/XMLSchema", "xsd");
      out.setIndentStep(2);
      filter.setContentHandler(out);
      filter.parse(query);
      out.flush();
    }
    catch (InstantiationException e) { 
      System.out.println(driverClass 
       + " could not be instantiated");
    }
    catch (ClassNotFoundException e) { 
      System.out.println(driverClass + " could not be found");
    }
    catch (Exception e) { // SQL, SAX, and IO
      e.printStackTrace();
      System.out.println(e);
    }
    
  }
  
}
