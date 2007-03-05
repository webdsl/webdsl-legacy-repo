import java.util.*;


public class Budget {

  private List   agencies = new ArrayList();
  private String year;
  
  public Budget(String year) {
    this.year = year;
  }
  
  public void add(Agency agency) {
    if (!agencies.contains(agency)) agencies.add(agency);     
  }

  public void add(Map lineItem) { 
           
    String agencyName = (String) lineItem.get("AgencyName");
    agencyName = escapeText(agencyName);
    String agencyCode = (String) lineItem.get("AgencyCode");
    String treasuryAgencyCode 
     = (String) lineItem.get("TreasuryAgencyCode");
    Agency agency = Agency.getInstance(agencyName, agencyCode, 
     treasuryAgencyCode, year);
    this.add(agency);
    
    String bureauName = (String) lineItem.get("BureauName");
    bureauName = escapeText(bureauName);
    String bureauCode = (String) lineItem.get("BureauCode");
    Bureau bureau = Bureau.getInstance(bureauName, bureauCode, 
     agencyCode, year);
    agency.add(bureau);
    
    // Names and codes of two accounts in different bureaus 
    // can be the same
    String accountName = (String) lineItem.get("AccountName");
    accountName = escapeText(accountName);
    String accountCode = (String) lineItem.get("AccountCode");
    String category    = (String) lineItem.get("BEACategory");
    Account account = Account.getInstance(accountName,  
     accountCode, category, bureauCode, agencyCode, year);
    bureau.add(account);
    
    // Names and codes of two subfunctions in different accounts 
    // can be the same
    String subfunctionTitle 
     = escapeText((String) lineItem.get("SubfunctionTitle"));
    String subfunctionCode
     = (String) lineItem.get("SubfunctionCode");
    String yearKey = year;
    if (!yearKey.equals("TransitionQuarter")) {
      yearKey = "FY" + year;
    }
    long amount
     = 1000L * Long.parseLong((String) lineItem.get(yearKey));
    Subfunction subfunction = new Subfunction(subfunctionTitle,
     subfunctionCode, amount);
    account.add(subfunction);
        
  } 

  public String getXML() {
        
    StringBuffer result = new StringBuffer("<Budget year='" 
     + this.year +"'>\r\n");
    Iterator iterator = agencies.iterator();
    while (iterator.hasNext()) {
      Agency agency = (Agency) iterator.next();
      result.append(agency.getXML());
    }
    result.append("</Budget>\r\n");
    return result.toString();
    
  }
  
  public static String escapeText(String s) {
   
    if (s.indexOf('&') != -1 || s.indexOf('<') != -1
     || s.indexOf('>') != -1 || s.indexOf('"') != -1
     || s.indexOf('\'') != -1 ) {
      StringBuffer result = new StringBuffer(s.length() + 6);
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '&') result.append("&amp;");
        else if (c == '<') result.append("&lt;");
        else if (c == '"') result.append("&quot;");
        else if (c == '\'') result.append("&apos;");
        else if (c == '>') result.append("&gt;");
        else result.append(c);
      }
      return result.toString();  
    }
    else {
      return s;   
    }
        
  }
  
}
