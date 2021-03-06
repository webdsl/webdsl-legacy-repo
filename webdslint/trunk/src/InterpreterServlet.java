

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semanticdomain.EvalState;
import semanticdomain.Request;
import semanticdomain.Context;
import semanticdomain.Env;
import semanticdomain.TemplateEnv;
import semanticdomain.value.VString;

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
		String name = "examples/simple.nwl";
		try {
			Reader reader = new FileReader(name);
			// Parse the file
			WebDSLScanner scanner = new WebDSLScanner(new BufferedReader(reader));
			Module m = (Module) parser.parse(scanner);
			// Evaluate the program
			Request req = new Request();
		    Enumeration<String> paramNames = request.getParameterNames();
		    while (paramNames.hasMoreElements()) {
		    	String paramName = paramNames.nextElement();
		    	String paramValue = request.getParameter(paramName);
//		    	req.put(new VString(paramName), new VString(paramValue));
		    	req.put(paramName, paramValue);
		    	System.out.println("adding param: " + paramName + "=" + paramValue);
		    }
			Context context = new Context();
			TemplateEnv templateEnv = new TemplateEnv();
			Env env = new Env();
			EvalState state = new EvalState();
			String rendered = m.evalR(state, req, context, templateEnv, env);
			PrintWriter out = response.getWriter();
			// print the output
			out.println("<html><body>");
			out.println(rendered);
			// Pretty print the source
			out.println("");
			out.println("<pre>");
			out.println("HTML output:");
			out.println("---------------");
			out.println(rendered.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			out.println("");
			out.println("Program source:");
			out.println("---------------");
			String pp = m.pp("");
			out.println(pp.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
			out.println("</pre>");
			out.println("</body></html>");
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
