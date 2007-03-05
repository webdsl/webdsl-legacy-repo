import java.sql.*;
import java.io.*;


public class SQLToXML {
        
  public static void main(String[] args ) {
        
    // Load the ODBC driver
    try {
      Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
    }
    catch (ClassNotFoundException e) {
      System.err.println("Could not load the JDBC-ODBC Bridge");
      return;
    }
    
    try {      
      Writer out = new OutputStreamWriter(System.out, "UTF8"); 
      out.write("<?xml version=\"1.0\"?>\r\n");
      out.write("<Budget>\r\n");
      writeAgencies(out);      
      out.write("</Budget>\r\n");
      out.close();      
    }
    catch (IOException e) {
      System.err.println(e);
    }

    
  }
  
  private static void writeAgencies(Writer out) 
   throws IOException {

    Connection conn = null;
    Statement stmnt = null;
    try {
      conn = DriverManager.getConnection(
       "jdbc:odbc:budauth", "", "");
      stmnt = conn.createStatement();
      String query = "SELECT DISTINCT AgencyName, AgencyCode"
       + " FROM BudgetAuthorizationTable;";
      ResultSet agencies = stmnt.executeQuery( query );

      while( agencies.next() ) {
        
        String agencyName = agencies.getString("AgencyName");
        agencyName = escapeText(agencyName);
        String agencyCode = agencies.getString("AgencyCode");
        out.write("  <Agency>\r\n");
        out.write("    <Name>" + agencyName + "</Name>\r\n");
        out.write("    <Code>" + agencyCode + "</Code>\r\n");
        writeBureaus(out, conn, agencyCode);
        out.write("  </Agency>\r\n");
         
      }
    }
    catch (SQLException e) {
      System.err.println(e);
      e.printStackTrace();       
    }
    finally {
      try {
        stmnt.close();
        conn.close();
      }
      catch(SQLException e) {
        System.err.println(e);
      }
    }
              
  }
  
  private static void writeBureaus(Writer out, Connection conn, 
   String agencyCode) throws IOException, SQLException {

    String query 
     = "SELECT DISTINCT BureauName, BureauCode "
     + "FROM BudgetAuthorizationTable WHERE AgencyCode='" 
     + agencyCode + "';";
    Statement stmnt = conn.createStatement();
    ResultSet bureaus = stmnt.executeQuery(query);

    while( bureaus.next() ) {
      String bureauName = bureaus.getString("BureauName");
      bureauName = escapeText(bureauName);
      String bureauCode = bureaus.getString("BureauCode");
      out.write("    <Bureau>\r\n");
      out.write("      <Name>" + bureauName + "</Name>\r\n");
      out.write("      <Code>" + bureauCode + "</Code>\r\n");
      writeAccounts(out, conn, agencyCode, bureauCode);
      out.write("    </Bureau>\r\n");
      out.flush();
    }        

  }
  
  private static void writeAccounts(Writer out, Connection conn, 
   String agencyCode, String bureauCode)
   throws IOException, SQLException {

    String query = "SELECT DISTINCT AccountName, AccountCode "
     + "FROM BudgetAuthorizationTable WHERE AgencyCode='" 
     + agencyCode + "' AND BureauCode='" + bureauCode + "';";
    Statement stmnt = conn.createStatement();
    ResultSet accounts = stmnt.executeQuery(query);

    while( accounts.next() ) {
      String accountName = accounts.getString("AccountName");
      accountName = escapeText(accountName);
      String accountCode = accounts.getString("AccountCode");
      out.write("      <Account>\r\n");
      out.write("        <Name>" + accountName + "</Name>\r\n");
      out.write("        <Code>" + accountCode + "</Code>\r\n");
      writeSubfunctions(
       out, conn, agencyCode, bureauCode, accountCode
      );
      out.write("      </Account>\r\n");
      out.flush();
    }        
        
  }
  
  private static void writeSubfunctions(Writer out,  
   Connection conn, String agencyCode, String bureauCode, 
   String accountCode) throws IOException, SQLException {

    String query = "SELECT * FROM BudgetAuthorizationTable"
     + " WHERE AgencyCode='" + agencyCode + "' AND BureauCode='" 
     + bureauCode + "' AND AccountCode='" + accountCode + "';";
    Statement stmnt = conn.createStatement();
    ResultSet subfunctions = stmnt.executeQuery(query);

    while( subfunctions.next() ) {
      String subfunctionTitle 
       = subfunctions.getString("SubfunctionTitle");
      subfunctionTitle = escapeText(subfunctionTitle);
      String subfunctionCode 
       = subfunctions.getString("SubfunctionCode");
      out.write("        <Subfunction>\r\n");
      out.write("          <Name>");
      out.write(subfunctionTitle);
      out.write("</Name>\r\n");
      out.write("          <Code>");
      out.write(subfunctionCode);
      out.write("</Code>\r\n");
      out.write("          <Amount year='TransitionQuarter'>"); 
      out.write(subfunctions.getInt("TransitionQuarter") 
       + "</Amount>\r\n");      
      for (int year = 1976; year <= 2006; year++) {
        String name = "FY" + year;
        long amt = subfunctions.getInt(name) * 1000L;
        out.write("          <Amount year='" + year + "'>");
        out.write(amt + "</Amount>\r\n");      
      }
      out.write("        </Subfunction>\r\n");
      out.flush();
    }        
        
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
