import org.xml.sax.*;
import java.util.Hashtable;


public class LocalXHTML implements EntityResolver {

  private Hashtable entities = new Hashtable();
  
  // fill the list of URLs
  public LocalXHTML() {
    
    // The XHTML 1.0 DTDs
    this.addMapping("-//W3C//DTD XHTML 1.0 Strict//EN",
     "http://www.cafeconleche.org/DTD/xhtml1-strict.dtd");
    this.addMapping("-//W3C//DTD XHTML 1.0 Transitional//EN",
     "http://www.cafeconleche.org/DTD/xhtml1-transitional.dtd");
    this.addMapping("-//W3C//DTD XHTML 1.0 Frameset//EN",
     "http://www.cafeconleche.org/DTD/xhtml1-frameset.dtd");

    // The XHTML 1.0 entity sets
    this.addMapping("-//W3C//ENTITIES Latin 1 for XHTML//EN",
     "http://www.cafeconleche.org/DTD/xhtml-lat1.ent");
    this.addMapping("-//W3C//ENTITIES Symbols for XHTML//EN",
     "http://www.cafeconleche.org/DTD/xhtml-symbol.ent");
    this.addMapping("-//W3C//ENTITIES Special for XHTML//EN",
     "http://www.cafeconleche.org/DTD/xhtml-special.ent");
   
  }

  private void addMapping(String publicID, String URL) {
    entities.put(publicID, URL);
  }
  
  public InputSource resolveEntity(String publicID, 
   String systemID) throws SAXException {
     
    if (entities.contains(publicID)) {
      String url = (String) entities.get(publicID);
      InputSource local = new InputSource(url);
      return local;
    }
    else return null;
    
  }
    
}
