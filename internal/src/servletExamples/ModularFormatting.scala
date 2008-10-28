package servletExamples ;

import javax.servlet._ ;
import javax.servlet.http._ ;
import scala.xml._ ;

class ModularFormatting extends ScalaHttpServlet {

  val headerMsg = Text("What follows is an example of modular formatting.");
  val footerMsg = Text("Complicated layout tasks can be encapsulated and outsourced. Try to append `?message=Hello' to the URL");

  /** helper function for the main page, provides header and a footer
   */
  def page( ns:Seq[Node] ) = {
    <html>
      <head>
        <title>ModularFormatting</title>
      </head>
      <body>
        <h2>Welcome</h2>
        <p>
          { headerMsg }
        </p>
        <p>
          { ns }
        </p>
        <hr/>
        <p>
          { footerMsg }
        </p>
        <h2>Bye!</h2>
      </body>
    </html>
  }

  /** applies beautify to every element in a sequence 
   *  we could also have used parens xs.map(beautify)
   *  we could also have omitted the dot and the delimiters `xs map beautify'
   *  we could also have used a for comprehension `for(x <- xs) yield beautify(x)
   */
  def beautify( xs:Seq[Node] ): Seq[Node] = xs.toList.map { beautify };

  /** this is a recursive procedure that adds some attributes to the tree
   */
  def beautify( n:Node ):Node = n match {

    case x @ <td>{ xs @ _* }</td> => 
      <td bgcolor="#AAAAFF" color="#222255">{ xs }</td>

    case <table>{ xs @ _* }</table> => 
      <table align="center">{ beautify( xs ) }</table>

    case Elem( ns, label, map, scope, xs @ _* ) =>  
      Elem( ns, label, map, scope, (xs.map { beautify }):_*)

    case _ => n
  }

  /** this function will take a node and put it in a table
   */
  def format( msg:Node ):Node = {
    <table>
      <tr>
        <td>{ msg }</td>
      </tr>
    </table>
  }

  /** returns a highlighted text node with the string given as arguemnt. if it
   *  is null, supplies a default string.
   */
  def getMessage( x:String ) = {
    if( x == null )
      <h1> This could be YOUR message ! </h1>
    else
      <h1> { x } </h1>
  }

  /** the entry method
   */
  def doGetXML( req:HttpServletRequest ) = {
    val theMessage = getMessage( req.getParameter("message") );
    beautify( page( format( theMessage )));
    /* page( List( format( theMessage ))); */
    
  }

}
