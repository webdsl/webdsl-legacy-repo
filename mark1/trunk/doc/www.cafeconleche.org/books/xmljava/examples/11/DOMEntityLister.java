import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.*;


public class EntityLister {

  // Store the entities that have already been printed
  private Set          printed = new HashSet();
  private NamedNodeMap entities;
  
  // Recursively descend the tree
  public void printEntities(Document doc) {
    
    DocumentType doctype = doc.getDoctype();
    entities = doctype.getEntities();
    seekEntities(doc);
    
  }

  // note use of recursion
  private void seekEntities(Node node) {
    
    int type = node.getNodeType();
    if (type == Node.ENTITY_REFERENCE_NODE) {
      EntityReference ref = (EntityReference) node;
      printEntityReference(ref);
    }
    
    if (node.hasChildNodes()) {
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        seekEntities(children.item(i));
      } 
    }
    
  }  
  
  private void printEntityReference(EntityReference ref) {
    
    String name = ref.getNodeName();
    if (!printed.contains(name)) {
      
      Entity entity   = (Entity) entities.getNamedItem(name);
      String publicID = entity.getPublicId();
      String systemID = entity.getSystemId();

      System.out.print(name + ": ");
      if (publicID != null) System.out.print(publicID + " ");
      if (systemID != null) System.out.print(systemID + " ");
      else { // Internal entities do not have system IDs
        System.out.print("internal entity");
      }
      System.out.println();
      
      printed.add(name);
    }
    
  }
  
  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java EntityLister URL");
      return;
    }
    String url = args[0];
    
    try {
      DocumentBuilderFactory factory 
       = DocumentBuilderFactory.newInstance();
       
      // By default JAXP does not include entity reference nodes
      // in the tree. You have to explicitly request them by 
      // telling DocumentBuilderFactory not to expand entity
      // references.
      factory.setExpandEntityReferences(false);
      DocumentBuilder parser = factory.newDocumentBuilder();
      
      // Read the document
      Document document = parser.parse(url); 
     
      // Print the entities
      EntityLister lister = new EntityLister();
      lister.printEntities(document);

    }
    catch (SAXException e) {
      System.out.println(url + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not read " + url
      ); 
    }
    catch (FactoryConfigurationError e) { 
      System.out.println("Could not locate a factory class"); 
    }
    catch (ParserConfigurationException e) { 
      System.out.println("Could not locate a JAXP parser"); 
    } 
     
  } // end main
  
}
