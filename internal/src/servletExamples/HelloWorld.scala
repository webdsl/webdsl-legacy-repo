package servletExamples ;

import java.io._ ;
import javax.servlet._ ;
import javax.servlet.http._ ;
import scala.xml._ ;

class HelloWorld extends ScalaHttpServlet {

  def doGetXML( req:HttpServletRequest ) = {
    <html>
      <head>
        <title>Hello World</title>
      </head>
      <body>
        <h1>Hello World</h1>
        <p>
          The time is <b>{ java.util.Calendar.getInstance().getTime().toString() }</b>
        </p>
      </body>
    </html>;
  }
  
/*
  override def init():Unit = super.init();
  override def init(sc:javax.servlet.ServletConfig):Unit = super.init(sc);
  override def service(req:javax.servlet.http.HttpServletRequest,
              resp:javax.servlet.http.HttpServletResponse) = 
                super.service(req, resp):Unit ;
                            
  override def service(req:javax.servlet.ServletRequest,
              resp:javax.servlet.ServletResponse):Unit =
                super.service(req,resp);
				*/
}
