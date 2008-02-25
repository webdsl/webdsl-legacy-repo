import AST.*;
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    if(args.length == 0)
      System.err.println("No files specified");
    for(String s : args) {
      try {
        Program p = new Parser().parse(new FileInputStream(s));
        for(String error : p.parseErrors())
          System.err.println(error);
        System.out.println(p.dumpTree());
        Collection<String> semErrors = new ArrayList<String>();
        p.collectErrors(semErrors);
        for(String error : semErrors)
	    System.err.println(error);
      }
      catch (FileNotFoundException e) {
        System.err.println("Could not find " + s);
      }
      catch (IOException e) {
        System.err.println("Error reading from " + s);
      }
    }
  }
}
