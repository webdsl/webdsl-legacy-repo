import org.webdsl.internal.MainServlet

class Main extends MainServlet {
  def pages = List(new example.Home(), new example.About(), new example.Calculator(), new example.Counter(0))
}
