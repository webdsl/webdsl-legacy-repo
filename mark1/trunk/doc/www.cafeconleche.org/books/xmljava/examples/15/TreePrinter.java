import org.jdom.*;
import org.jdom.input.SAXBuilder;
import java.io.IOException;
import java.util.*;


public class TreePrinter {

  // Recursively descend the tree
  public static void process(Element element) {
    
    inspect(element);
    List content = element.getContent();
    Iterator iterator = content.iterator();
    while (iterator.hasNext()) {
      Object o = iterator.next();
      if (o instanceof Element) {
        Element child = (Element) o;
        process(child);
      }
    }
    
  }

  // Print the properties of each element
  public static void inspect(Element element) {
    
    if (!element.isRootElement()) {
      // Print a blank line to separate it from the previous
      // element.
      System.out.println(); 
    }
    
    String qualifiedName = element.getQualifiedName();
    System.out.println(qualifiedName + ":");
    
    Namespace namespace = element.getNamespace();
    if (namespace != Namespace.NO_NAMESPACE) {
      String localName = element.getName();
      String uri = element.getNamespaceURI();
      String prefix = element.getNamespacePrefix();
      System.out.println("  Local name: " + localName);
      System.out.println("  Namespace URI: " + uri);
      if (!"".equals(prefix)) {
        System.out.println("  Namespace prefix: " + prefix);
      }
    }
    List attributes = element.getAttributes();
    if (!attributes.isEmpty()) {
      Iterator iterator = attributes.iterator();
      while (iterator.hasNext()) {
        Attribute attribute = (Attribute) iterator.next();
        String name = attribute.getName();
        String value = attribute.getValue();
        Namespace attributeNamespace = attribute.getNamespace();
        if (attributeNamespace == Namespace.NO_NAMESPACE) {
          System.out.println("  " + name + "=\"" + value + "\""); 
        }
        else {
          String prefix = attributeNamespace.getPrefix();
          System.out.println(
           "  " + prefix + ":" + name + "=\"" + value + "\""); 
        }
      }
    }
    
    List namespaces = element.getAdditionalNamespaces();
    if (!namespaces.isEmpty()) {
      Iterator iterator = namespaces.iterator();
      while (iterator.hasNext()) {
        Namespace additional = (Namespace) iterator.next();
        String uri = additional.getURI();
        String prefix = additional.getPrefix();
          System.out.println(
           "  xmlns:" + prefix + "=\"" + uri + "\""); 
      }
    }
    
  }
  
  public static void main(String[] args) {

    if (args.length <= 0) {
      System.out.println("Usage: java TreePrinter URL");
      return;
    }
    
    String url = args[0];
    
    try {
      SAXBuilder parser = new SAXBuilder();
      
      // Parse the document
      Document document = parser.build(url); 
      
      // Process the root element
      process(document.getRootElement());

    }
    catch (JDOMException e) {
      System.out.println(url + " is not well-formed.");
    }
    catch (IOException e) { 
      System.out.println(
       "Due to an IOException, the parser could not encode " + url
      ); 
    }
     
  } // end main

}
