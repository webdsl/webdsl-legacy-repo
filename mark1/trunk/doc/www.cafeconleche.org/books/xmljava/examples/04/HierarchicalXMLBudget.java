import java.io.*;
import java.util.*;


public class HierarchicalXMLBudget {

  public static void convert(List budgetData, String year, 
   OutputStream out) throws IOException { 
     
    Budget budget = new Budget(year);
    Iterator records = budgetData.iterator();
    while (records.hasNext()) {
      Map lineItem = (Map) records.next();
      budget.add(lineItem);
    }

    Writer wout = new OutputStreamWriter(out, "UTF8"); 
    wout.write("<?xml version=\"1.0\"?>\r\n");
    wout.write(budget.getXML());
    wout.flush();
        
  }

  public static void main(String[] args) {
  
    try {
        
      if (args.length < 2) {
        System.out.println(
         "Usage: HierarchicalXMLBudget year infile outfile");
        return;
      }
      
      // simple error checking on the year value
      try {
        if (!args[0].equals("TransitionQuarter")) {
          Integer.parseInt(args[0]);
        }
      }
      catch (NumberFormatException e) {
        System.out.println(
         "Usage: HierarchicalXMLBudget year infile outfile");
        return;        
      }
      
      InputStream in = new FileInputStream(args[1]); 
      OutputStream out; 
      if (args.length < 3) {
        out = System.out;
      }
      else {
        out = new FileOutputStream(args[2]); 
      }

      List results = BudgetData.parse(in);
      convert(results, args[0], out);
    }
    catch (IOException e) {
      System.err.println(e);       
    }
  
  }

}
