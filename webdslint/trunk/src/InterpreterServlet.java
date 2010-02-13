

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AST.Module;
import AST.WebDSLParser;
import AST.WebDSLScanner;

/**
 * Servlet implementation class ServInt
 */
public class InterpreterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterpreterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebDSLParser parser = new WebDSLParser();
		String name = "examples/helloworld.nwl";
		try {
			Reader reader = new FileReader(name);
			// Parse the file
			WebDSLScanner scanner = new WebDSLScanner(new BufferedReader(reader));
			Module m = (Module) parser.parse(scanner);
			// Evaluate the program
			String value = m.eval();
			PrintWriter out = response.getWriter();
			out.println(value);
			// Pretty print the source
			out.println();
			out.println("Program source:");
			out.println("---------------");
			out.println();
			String pp = m.pp("");
			out.println(pp);
			out.close();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
