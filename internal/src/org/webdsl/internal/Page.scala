package org.webdsl.internal

import java.io._
import javax.servlet.http._

object PageMode extends Enumeration {
  type PageMode = Value
  val DataBind, Action, Render = Value
}

case class IgnoreMe

abstract case class Page(ign : IgnoreMe) extends Forms {
  var sectionDepth = 1
  var style = new Style
  var out : PrintWriter = null
  var request : HttpServletRequest = null
  var response : HttpServletResponse = null
  protected var mode = PageMode.DataBind
  
  def ui
  def title : String
  def name : String
  
  def init(out : PrintWriter, req : HttpServletRequest, res : HttpServletResponse) {
    this.out = out
    this.request = req
    this.response = res
  }
  
  def resetCounters {
    actionCounter = 0
    formCounter = 0
    inputCounter = 0
  }
  
  def write(s : String) {
    out.print(s)
  }
  
  def doDatabind {
    mode = PageMode.DataBind
    ui
  }
  
  def doAction {
    mode = PageMode.Action
    ui
  }
  
  def doRender {
    mode = PageMode.Render
    write("<html><head>")
    write(style.toString)
    write("<title>" + title + "</title>")
    write("</head><body>")
    ui
    write("</body></html>")
  }
  
  def header(s : String) {
    mode match {
      case PageMode.Render => write("<h" + sectionDepth + " class=\"header\">" + s + "</h" + sectionDepth + ">")
      case _ =>
    }
  }
  
  def header(content : => Unit) {
    mode match {
      case PageMode.Render => {
        write("<h" + sectionDepth + " class=\"header\">")
        content
        write("</h" + sectionDepth + ">")
      }
      case _ => content
    }
  }
  
  def text(s : String) {
    mode match {
      case PageMode.Render => {
        write(/*"<span class=\"text\">" + */s/* + "</span>"*/)
      }
      case _ =>
    }
  }
  
  def block(s : String)(content : => Unit) {
    mode match {
      case PageMode.Render => {
        write("<div class=\"" + s + "\">")
        content
        write("</div>")
      }
      case _ => content
    }
  }
  
  def img(s : String) {
    mode match {
      case PageMode.Render => {
        write("<img src=\"" + s + "\" class=\"img\"/>")
      }
      case _ =>
    }
  }  
  
  def br {
    mode match {
      case PageMode.Render => {
        write("<br/>")
      }
      case _ =>
    }
  }

  def hr {
    mode match {
      case PageMode.Render => {
        write("<hr class=\"hr\"/>")
      }
      case _ =>
    }
  }

  def navigate(url : String)(content : => Unit) {
    mode match {
      case PageMode.Render => {
        write("<a href=\"" + url + "\" class=\"navigate\">")
        content
        write("</a>")
      }
      case _ => content
    }
  }

  def navigate(p : Page)(content : => Unit) {
    mode match {
      case PageMode.Render => {
        write("<a href=\"" + buildPageUrl(p) + "\" class=\"navigate\">")
        content
        write("</a>")
      }
      case _ => content
    }
  }
  
  def buildPageUrl(p : Page) : String = {
    val c = p.getClass
    var queryStr = new StringBuilder
    for(i <- 0 until p.productArity) {
      val value = p.productElement(i)
      queryStr.append("/" + value.toString)
    }
    request.getContextPath + "/" + p.name + queryStr.toString
  }
  
  def section(content : => Unit) {
    mode match {
      case PageMode.Render => {
        write("<div class=\"section\">")
        sectionDepth += 1
        content
        sectionDepth -= 1
        write("</div>")
      }
      case _ => content
    }
  }
  
  def list(content : => Unit) {
    mode match {
      case PageMode.Render => {
        write("<ul class=\"list\">")
        content
        write("</ul>")
      }
      case _ => content
    }
  }
  
  def listitem(s : String) {
    mode match {
      case PageMode.Render => {
        write("<li class=\"listitem\">" + s + "</li>")
      }
      case _ => 
    }
  }
  
  def listitem(content : => Unit) {
    mode match {
      case PageMode.Render => {
        write("<li class=\"listitem\">")
        content
        write("</li>")
      }
      case _ => content
    }
  }
  
  def goto(url : String) {
    response.sendRedirect(url)
  }

  def goto(p : Page) {
    response.sendRedirect(buildPageUrl(p))
  }
  
  // Checks
  def printAll() {
   for (i <- 0 until productArity) {
     println(productElement(i))
   }
  }

  def this() = this(new IgnoreMe)

 if (productArity == 1 && (productElement(0) == IgnoreMe()))
   throw new IllegalStateException("Page classes must be defined as case classes")
}