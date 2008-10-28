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
    navigate(new Home()) { text("Home") }; text(" | ")
    navigate(new Counter(10)) { text("Counter demo") }; text(" | ")   
    navigate(new About()) { text("About") }
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
  
  var counter = 0
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
    form {
      counter = inputHidden(counter)
      text("Counter: " + counter)
      action("Up") {
        counter += 1
      }
      action("Down") {
        counter -= 1
      }
    }

  }
}
