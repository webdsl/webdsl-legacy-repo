package servletExamples ;

import javax.servlet._ ;
import javax.servlet.http._ ;

import scala.xml._ ;

class ParamPassing extends ScalaHttpServlet {
  def doGetXML( req:HttpServletRequest ) = {

    // get parameter from the request
    var textStr  = req.getParameter("action");

    // it might be null

    if( textStr == null )
      textStr = "(no argument given)";

    // last expression in a { block } is the return value

    <html>
      <head>
        <title>Hello World</title>
      </head>
      <body>
        <h1>Param Passing</h1>
        <p>The param "action" is set to 
          "<b>{ Text( textStr )}</b>"
        </p>
        <p>Try these links:
          <ul>
            <li><a href="doThis?action=Foo">doThis?action=Foo</a></li>
            <li><a href="doThis?action=Bar">doThis?action=Bar</a></li>
            <li>
              <a href="doThis?action=My%20Name%20is...">
                doThis?action=My%20Name%20is...
              </a>
            </li>
          </ul>
        </p>
      </body>
    </html>
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
