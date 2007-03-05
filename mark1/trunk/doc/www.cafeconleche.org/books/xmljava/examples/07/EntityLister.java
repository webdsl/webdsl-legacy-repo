import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.IOException;
import java.util.StringTokenizer;


public class EntityLister extends DefaultHandler {

  private UnparsedCache cache;
  
  public EntityLister(UnparsedCache cache) {
    this.cache = cache;
  }
  
  public void startElement(String namespaceURI, String localName,
   String qualifiedName, Attributes attributes) {
     
    for (int i = 0; i < attributes.getLength(); i++) {
      
      if (attributes.getType(i).equals("NOTATION")) {  
        Notation n = cache.getNotation(attributes.getValue(i));
        System.out.println("Element " + qualifiedName 
         + " has notation " + n);
      }
      else if (attributes.getType(i).equals("ENTITY")) {
        UnparsedEntity e = cache.getUnparsedEntity(
         attributes.getValue(i));
        System.out.println("Entity: " + e);
      }
      else if (attributes.getType(i).equals("ENTITIES")) {
        String entityNames = attributes.getValue(i);
        StringTokenizer st 
         = new StringTokenizer(entityNames);
        while (st.hasMoreTokens()) {
           String name = st.nextToken();
           UnparsedEntity e = cache.getUnparsedEntity(name);
           System.out.println("Entity: " + e);
        }
        
      }

    }
    
  }
  
  
  public static void main(String[] args) {
  
    if (args.length <= 0) {
      System.out.println("Usage: java EntityLister URL");
      return;
    }
    String document = args[0];
    
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader();
      
      // I want to use qualified names
      parser.setFeature(
       "http://xml.org/sax/features/namespace-prefixes", true);
      
      UnparsedCache cache = new UnparsedCache();
      parser.setDTDHandler(cache);
      parser.setContentHandler(new EntityLister(cache));
      
      parser.parse(document);
    }
    catch (Exception e) {
      System.out.println("Could not read document because " 
       + e.getMessage());
    }
  
  }

}
