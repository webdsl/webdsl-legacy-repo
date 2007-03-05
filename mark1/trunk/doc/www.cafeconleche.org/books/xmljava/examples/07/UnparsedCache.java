import org.xml.sax.*;
import java.util.Hashtable;


public class UnparsedCache implements DTDHandler {

  private Hashtable notations = new Hashtable();
  private Hashtable entities = new Hashtable();
  
  public void notationDecl(String name, String publicID, 
   String systemID) {
    
    System.out.println(name);
    notations.put(name, new Notation(name, publicID, systemID));
   
  }
   
  public void unparsedEntityDecl(String name, String publicID, 
   String systemID, String notationName) {
     
    entities.put(name, new UnparsedEntity(name, publicID, 
     systemID, notationName));
    
  }
  
  public UnparsedEntity getUnparsedEntity(String name) {
    System.out.println("Getting " + name);
    return (UnparsedEntity) entities.get(name); 
  }
  
  public Notation getNotation(String name) {
    System.out.println("Getting " + name);
    return (Notation) notations.get(name); 
  }
    
}
