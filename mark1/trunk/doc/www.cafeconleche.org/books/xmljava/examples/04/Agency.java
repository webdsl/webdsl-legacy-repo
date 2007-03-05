import java.util.*;


public class Agency {
 
  private String code;
  private String name;
  private String treasuryCode;
  private String year;
  
  private List   bureaus = new ArrayList();
  
  private static Map instances = new HashMap();

  // A private constructor so instantiators 
  // have to use the factory method
  private Agency(String name, String code, String treasuryCode, 
	 String year) {
        
    this.name = name;
    this.code = code;
    this.treasuryCode = treasuryCode;
    this.year = year;
    
  }
  
  public static Agency getInstance(String name, String code, 
	 String treasuryCode, String year) {
        
    // Agencies can be uniquely identified by code alone
    String key = code+" "+year;
    Agency agency = (Agency) instances.get(key);
    if (agency == null) {
      agency = new Agency(name, code, treasuryCode, year);
      instances.put(key, agency);
    }
    
    return agency;
        
  }
  
  public void add(Bureau b) {
    if (!bureaus.contains(b)) {
        bureaus.add(b);
    }
  }
  
  public String getXML() {
        
    StringBuffer result = new StringBuffer("  <Agency>\r\n");
    result.append("    <Name>" + name + "</Name>\r\n");
    result.append("    <Code>" + code + "</Code>\r\n");
    result.append("    <TreasuryAgencyCode>" + treasuryCode 
     + "</TreasuryAgencyCode>\r\n");
    Iterator iterator = bureaus.iterator();
    while (iterator.hasNext()) {
      Bureau bureau = (Bureau) iterator.next();
      result.append(bureau.getXML());
    }
    result.append("  </Agency>\r\n");
    return result.toString();
    
  }  
           
}
