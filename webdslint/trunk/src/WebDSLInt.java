import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import AST.Module;
import AST.WebDSLParser;
import AST.WebDSLScanner;

class WebDSLInt {
	public static void main(String args[]) {
		if(args.length != 1) {
			System.out.println("WebDSL expects a single file name as command line argument");
			System.exit(1);
		}
		WebDSLParser parser = new WebDSLParser();
		try {
			String name = args[0];
			Reader reader = new FileReader(name);
			WebDSLScanner scanner = new WebDSLScanner(new BufferedReader(reader));
			Module p = (Module)parser.parse(scanner);
			String pp = p.pp("");
			System.out.println(pp);
			reader.close();

			/*
			Collection errors = p.errors();
			if(!errors.isEmpty()) {
				for(Iterator iter = errors.iterator(); iter.hasNext(); )
					System.out.println(iter.next());
			}
			else {
				System.out.println("OK");
			}
			*/

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return;
		}
	}
}
