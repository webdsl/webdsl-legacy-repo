import org.jdom.filter.Filter;
import org.jdom.*;
import java.util.List;


public class StylesheetFilter implements Filter {

  // This filter is read-only. Nothing can be added or removed.
  public boolean canAdd(Object o) {
    return false; 
  }
  
  public boolean canRemove(Object o) {
    return false;  
  }
  
  public boolean matches(Object o) {
   
    if (o instanceof ProcessingInstruction) {
      ProcessingInstruction pi = (ProcessingInstruction) o; 
      if (pi.getTarget().equals("xml-stylesheet")) {
        // Test to see if we're outside the root element
        if (pi.getParent() == null) {
          Document doc = pi.getDocument();
          Element root = doc.getRootElement();
          List content = doc.getContent();
          if (content.indexOf(pi) < content.indexOf(root)) {
            // In prolog
            return true;
          }
        }
      }
    }
    return false;
   
  }
    
}
