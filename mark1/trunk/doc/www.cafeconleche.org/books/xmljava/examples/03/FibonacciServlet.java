import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.math.BigInteger;


public class FibonacciServlet extends HttpServlet {

  public void doGet(
   HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
    
    //read the query string
    int numberOfGenerations = 10;
    String generations = request.getParameter("generations");
    try {
      numberOfGenerations = Integer.parseInt(generations);
    }
    catch (Exception e) { // NumberFormat or NullPointerException
      // use default value of 10 
    }
     
    response.setContentType("text/xml; charset=UTF-8");               
    PrintWriter out = response.getWriter();
    
    out.println("<?xml version=\"1.0\"?>");  
    out.print  ("<?xml-stylesheet ");
    out.println(
     "type='text/css' href='/xml/styles/fibonacci.css'?>");  
    out.println("<Fibonacci_Numbers>"); 
     
    BigInteger low  = BigInteger.ONE;
    BigInteger high = BigInteger.ONE;      
    for (int i = 1; i <= numberOfGenerations; i++) {
      out.print("  <fibonacci index=\"" + i + "\">");
      out.print(low);
      out.println("</fibonacci>");
      
      BigInteger temp = high;
      high = high.add(low);
      low = temp;
    }
    out.println("</Fibonacci_Numbers>");  
        
    out.close();
    
  }

  public void doPost(
   HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
    
    doGet(request, response);
    
  }

}
