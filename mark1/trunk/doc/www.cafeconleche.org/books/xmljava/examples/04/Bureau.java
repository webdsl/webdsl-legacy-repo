import java.util.*;


public class Bureau {
 
  // Agency code plus bureau code uniquely identify a bureau  
  // Bureau code alone is definitely not sufficient
  private String code;
  private String name;
  private String year;
  private String agencyCode;
  
  private List   accounts = new ArrayList();
  
  private static Map instances = new HashMap();

  // Use a private constructor so instantiators 
  // have to use the factory method
  private Bureau(String name, String code, String agencyCode, 
    String year) {
        
    this.name = name;
    this.code = code;
    this.agencyCode = agencyCode;
    this.year = year;
    
  }
  
  public static Bureau getInstance(String name, String code, 
   String agencyCode, String year) {
        
    String key = agencyCode+" "+code+" "+year;
    Bureau bureau = (Bureau) instances.get(key);
    if (bureau == null) {
      bureau = new Bureau(name, code, agencyCode, year);
      instances.put(key, bureau);
    }
    
    return bureau;
        
  }
  
  public void add(Account account) {
    if (!accounts.contains(account)) accounts.add(account);     
  }
  
  public String getXML() {
        
    StringBuffer result = new StringBuffer("    <Bureau>\r\n");
    result.append("      <Name>" + name + "</Name>\r\n");
    result.append("      <Code>" + code + "</Code>\r\n");
    Iterator iterator = accounts.iterator();
    while (iterator.hasNext()) {
      Account account = (Account) iterator.next();
      result.append(account.getXML());
    }
    result.append("    </Bureau>\r\n");
    return result.toString();
    
  }
          
}
