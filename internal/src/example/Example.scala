package example

import org.webdsl.internal._

object DefaultStyle extends Style {
  block("headerblock") >> header {
    fontsize = 30 pt;
    width = 50 percent;
    bgcolor = "#c0c0c0";
  }  
  section >> header {
    color = "#ff00ff"
  }
  body {
    fontfamily = "Helvetica, Arial, Verdana, sans-serif"
  }
}

trait MainTemplate extends Page {
  def body
  
  style = DefaultStyle
  
  def ui {
    block("headerblock") {
      header {
	    navigate("http://lol.com") { text("Internal WebDSL Demo") }
	  }
    }
    hr
    body
    hr
    navigate(new Home()) { text("Home") }; text(" | ")
    navigate(new Counter(10)) { text("Counter demo") }; text(" | ")   
    navigate(new About()) { text("About") }
    br
    img("http://localhost/~zef/globe.png")
  }
}

class Home extends MainTemplate {
  
  def name = "home"
  def params = Nil
  
  def title = "Home"
   
  def body {
    section {
      header("Let's count")
      list {
		for(i <- 0 to 10 filter (e => e % 2 == 0) reverse) {
          listitem("Count " + i)
	    }
      }
    }
    /*hr
    def submit(p1 : Int, p2 : Int) {
      
    }
    form {
      action("Submit") { submit(1, 2) }
    }
    
    br*/
  }
}

class About extends MainTemplate {
  def name = "about"
  def params = Nil
  
  def title = "About"
  
  def body {
    header("About")
    text("""Internal WebDSL is an internal version of the WebDSL language, implemented as an internal DSL inside of Scala.""")
  }
}

class Counter(var n : Int) extends MainTemplate {
  def name = "counter"
  def params = List("n")
  def title = "Counter"  
  
  def body {
    header("Counter: " + n)
    navigate(new Counter(n + 1)) { text("Up") }
    text(" | ")
    navigate(new Counter(n - 1)) { text("Down") }
    list {
      for(i <- 1 to n) {
        listitem(i.toString)
	  }
    }

  }
}
