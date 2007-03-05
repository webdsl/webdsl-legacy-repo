import java.io.*;
import java.util.*;


public class AttributesXMLBudget {

  public static void convert(List data, OutputStream out) 
   throws IOException {
      
    Writer wout = new OutputStreamWriter(out, "UTF8"); 
    wout.write("<?xml version=\"1.0\"?>\r\n");
    wout.write("<Budget>\r\n");
          
    Iterator records = data.iterator();
    while (records.hasNext()) {
      wout.write("  <ListItem");
      Map record = (Map) records.next();

      // write the attributes
      writeAttribute(wout, "AgencyCode", record);
      writeAttribute(wout, "AgencyName", record);
      writeAttribute(wout, "BureauCode", record);
      writeAttribute(wout, "BureauName", record);
      writeAttribute(wout, "AccountCode", record);
      writeAttribute(wout, "AccountName", record);
      writeAttribute(wout, "TreasuryAgencyCode", record);
      writeAttribute(wout, "SubfunctionCode", record);
      writeAttribute(wout, "SubfunctionTitle", record);
      writeAttribute(wout, "BEACategory", record);
      writeAttribute(wout, "On-Off-BudgetIndicator", record);
      wout.write(">\r\n");
      writeAmount(wout, 1976, record);
      wout.write("    <Amount year='TransitionQuarter'>");
      wout.write(
        escapeText((String) record.get("TransitionQuarter"))
      );
      wout.write("</Amount>\r\n");
      for (int year=1977; year <= 2006; year++) {
        writeAmount(wout, year, record);
      }
      wout.write("  </ListItem>\r\n");
    }
    wout.write("</Budget>\r\n");
    wout.flush();
        
  } 

  // Just a couple of private methods to factor out repeated code 
  private static void writeAttribute(Writer out, String name, 
   Map record) throws IOException {
    out.write(" " + name + "='" 
     + escapeText((String) record.get(name)) + "'");       
  }

  private static void writeAmount(Writer out, int year, 
   Map record) throws IOException {
    out.write("    <Amount year='" + year + "'>");
    out.write(escapeText((String) record.get("FY" + year)));
    out.write("</Amount>\r\n");
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

  public static void main(String[] args) {
  
    try {
        
      if (args.length < 1) {
        System.out.println(
         "Usage: AttributesXMLBudget infile outfile"
        );
        return;
      }
      
      InputStream in = new FileInputStream(args[0]); 
      OutputStream out; 
      if (args.length < 2) {
        out = System.out;
      }
      else {
        out = new FileOutputStream(args[1]); 
      }

      List results = BudgetData.parse(in);
      convert(results, out);
    }
    catch (IOException e) {
      System.err.println(e);       
    }
  
  }

}
