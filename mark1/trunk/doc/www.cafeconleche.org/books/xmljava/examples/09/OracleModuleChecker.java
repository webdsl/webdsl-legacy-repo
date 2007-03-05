import oracle.xml.parser.v2.XMLDOMImplementation;
import org.w3c.dom.DOMImplementation;


public class OracleModuleChecker {

  public static void main(String[] args) {
     
    // parser dependent
    DOMImplementation implementation = new XMLDOMImplementation();
    String[] features = {"Core", "XML", "HTML", "Views", 
     "StyleSheets", "CSS", "CSS2", "Events", "UIEvents", 
     "MouseEvents", "MutationEvents", "HTMLEvents", "Traversal", 
     "Range"};
    
    for (int i = 0; i < features.length; i++) {
      if (implementation.hasFeature(features[i], "2.0")) {
        System.out.println("Oracle supports " + features[i]);
      } 
      else {
        System.out.println("Oracle does not support " 
         + features[i]);
      } 
    }
  
  }

}
