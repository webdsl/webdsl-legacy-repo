import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;


public class FibonacciXMLRPCXSLServlet extends HttpServlet {

  private Templates stylesheet;
  
  // Load the stylesheet
  public void init() throws ServletException {  
    
    try {
      TransformerFactory xformFactory 
       = TransformerFactory.newInstance();
      Source source   = new StreamSource("FibonacciXMLRPC.xsl");
      this.stylesheet = xformFactory.newTemplates(source);
    }
    catch (TransformerException e) { 
      throw new ServletException(
       "Could not load the stylesheet", e); 
    }
    
  }   
  
  // Respond to an XML-RPC request
  public void doPost(HttpServletRequest servletRequest,
   HttpServletResponse servletResponse)
   throws ServletException, IOException {

    servletResponse.setContentType("text/xml; charset=UTF-8");                    
     
    try {
      InputStream in  = servletRequest.getInputStream();
      Source source   = new StreamSource(in);
      PrintWriter out = servletResponse.getWriter();
      Result result   = new StreamResult(out);
      Transformer transformer = stylesheet.newTransformer();
      transformer.transform(source, result);
      servletResponse.flushBuffer();
      out.flush(); 
      out.println();
    }
    catch (TransformerException e) {
      // If we get an exception at this point, it's too late to
      // switch over to an XML-RPC fault. 
      throw new ServletException(e); 
    }
    
  }

}
