import AST.*;
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
      if(args.length != 1) {
	  System.err.println("Provide name of main module only");
	  System.exit(1);
      }
      Program p = new Program();
      Module m = Program.parseModule(args[0]);
      
      p.addModule(m);

      // System.out.println(p.dumpTree());
      
      Collection<String> semErrors = new ArrayList<String>();
      p.collectErrors(semErrors);
      for(String error : semErrors)
	  System.err.println(error);
      if(!semErrors.isEmpty())
	  System.exit(1);
      
      for(JavaCompilationUnit c : p.code()) {
	  try {
	      PrintWriter file = new PrintWriter(new FileWriter(c.getName() + ".ejava"));
	      file.println(c.getCode());
	      file.close();
	  } catch( IOException e) {
	      System.err.println(e.getMessage());
	  }
      }
  }



}
