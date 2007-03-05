import java.util.*;


public class Account {
 
  // An account is uniquely identified by account code,
  // bureau code, agency code and BEA category
  private String code;
  private String name;
  private String BEACategory;
  private String bureauCode;
  private String agencyCode;
  private String year;
  
  private List   subfunctions = new ArrayList();
  
  private static Map instances = new HashMap();

  // Use a private constructor so clients 
  // have to use the factory method
  private Account(String name, String code, String BEACategory, 
   String bureauCode, String agencyCode, String year) {
        
    this.name = name;
    this.code = code;
    this.BEACategory = BEACategory;
    this.bureauCode = bureauCode;
    this.agencyCode = agencyCode;
    this.year = year;
    
  }
  
  public static Account getInstance(String name, String code, 
   String BEACategory, String bureauCode, String agencyCode, 
   String year) {
        
    String key = code + " " + BEACategory + " " + bureauCode 
     + " " + agencyCode + " " + year;
    Account account = (Account) instances.get(key);
    if (account == null) {
      account = new Account(name, code, BEACategory, bureauCode, 
       agencyCode, year);
      instances.put(key, account);
    }
    
    return account;
        
  }
  
  public void add(Subfunction sfx) {
    if (!subfunctions.contains(sfx)) subfunctions.add(sfx);     
  }
  
  public String getXML() {
        
    StringBuffer result = new StringBuffer();
    result.append("      <Account>\r\n");
    result.append("        <Name>" + name + "</Name>\r\n");
    result.append("        <Code>" + code + "</Code>\r\n");
    result.append("        <BEACategory>" + BEACategory 
     + "</BEACategory>\r\n");
    Iterator iterator = subfunctions.iterator();
    while (iterator.hasNext()) {
      Subfunction subfunction = (Subfunction) iterator.next();
      result.append(subfunction.getXML());
    }
    result.append("      </Account>\r\n");
    return result.toString();
    
  }
           
}
