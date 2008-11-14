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
  
  var query = ""
  
  def ui {
    block("headerblock") {
      header {
	    navigate("http://lol.com") { text("Internal WebDSL Demo") }
	  }
    }
    hr
    body
    hr
    navigate(Home()) { text("Home") }; text(" | ")
    navigate(Calculator()) { text("Calculator") }; text(" | ")
    navigate(Counter(10)) { text("Counter demo") }; text(" | ")   
    navigate(About()) { text("About") }
    br
    form {
      text("Query: ")
      query = input(query)
      action("Search") {
        goto("http://www.google.com/search?q=" + response.encodeUrl(query))
      }
    }

  }
}

case class Home extends MainTemplate {
  def body {
    section {
      header("Let's count")
      list {
		for(i <- 0 to 10 filter (e => e % 2 == 0) reverse) {
          listitem("Count " + i)
	    }
      }
    }
  }
}

case class About extends MainTemplate {
  def body {
    header("About")
    text("""Internal WebDSL is an internal version of the WebDSL language, implemented as an internal DSL inside of Scala.""")
  }
}

case class Calculator extends MainTemplate {
  override def name = "calc"
  
  var n : Long = 2
  
  def body {
    form {
      n = input(n)
      
      action("+1") {
        n += 1
      }
      action("^2") {
        n *= n
      }
      action("fac") {
        def fac(i : Long) : Long =
          if(i == 0)
            1
          else
            i * fac(i-1)
        n = fac(n)
      }
      action("Sqr") {
        n = Math.sqrt(n.intValue)
      }
    }

  }
}
case class Counter(n : Int) extends MainTemplate {
  
  def body {
    header("Counter: " + n)
    navigate(Counter(n + 1)) { text("Up") }
    text(" | ")
    navigate(Counter(n - 1)) { text("Down") }
    list {
      for(i <- 1 to n) {
        listitem(i.toString)
	  }
    }
  }
}
