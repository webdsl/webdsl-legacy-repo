import java.util.*;


public class Subfunction {
 
  private String code;
  private String title;
  private long   amount;
    
  public Subfunction(String title, String code, long amount) {
        
    this.title  = title;
    this.code   = code;
    this.amount = amount;
    
  }
  
  public String getXML() {
        
    StringBuffer result 
      = new StringBuffer("        <Subfunction>\r\n");
    result.append("          <Name>" + title + "</Name>\r\n");
    result.append("          <Code>" + code + "</Code>\r\n");
    result.append("          <Amount>");
    result.append(amount + "</Amount>\r\n");
    result.append("        </Subfunction>\r\n");
    return result.toString();
    
  } 
               
}
