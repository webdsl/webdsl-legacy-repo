package org.webdsl.internal

import java.io._
import javax.servlet.http._

trait Page {
  
  var sectionDepth = 1
  var actionCounter = 0
  var formCounter = 0
  var style = new Style
  var out : PrintWriter = null
  var req : HttpServletRequest = null
  
  def ui
  def title : String
  def name : String
  def params : List[String]
  
  def init(out : PrintWriter, req : HttpServletRequest) {
    this.out = out
    this.req = req
  }
  
  def write(s : String) {
    out.print(s)
  }
  
  def render {
    write("<html><head>")
    write(style.toString)
    write("<title>" + title + "</title>")
    write("</head><body>")
    ui
    write("</body></html>")
  }
  
  def header(s : String) {
    write("<h" + sectionDepth + " class=\"header\">" + s + "</h" + sectionDepth + ">")
  }
  
  def header(content : => Unit) {
    write("<h" + sectionDepth + " class=\"header\">")
    content
    write("</h" + sectionDepth + ">")
  }
  
  def text(s : String) {
    write(/*"<span class=\"text\">" + */s/* + "</span>"*/)
  }
  
  def block(s : String)(content : => Unit) {
    write("<div class=\"" + s + "\">")
    content
    write("</div>")
  }
  
  def img(s : String) {
    write("<img src=\"" + s + "\" class=\"img\"/>")
  }  
  
  def br {
    write("<br/>")
  }

  def hr {
    write("<hr class=\"hr\"/>")
  }

  def navigate(url : String)(content : => Unit) {
    write("<a href=\"" + url + "\" class=\"navigate\">")
    content
    write("</a>")
  }

  def navigate(p : Page)(content : => Unit) {
    val c = p.getClass
    var queryStr = new StringBuilder
    for(fn <- p.params) {
      val value = c.getMethod(fn).invoke(p)
      queryStr.append("/" + value.toString)
    }
    write("<a href=\"" + req.getContextPath + "/" + p.name + queryStr.toString + "\" class=\"navigate\">")
    content
    write("</a>")
  }
  
  def section(content : => Unit) {
    write("<div class=\"section\">")
    sectionDepth += 1
    content
    sectionDepth -= 1
    write("</div>")
  }
  
  def list(content : => Unit) {
    write("<ul class=\"list\">")
    content
    write("</ul>")
  }
  
  def listitem(s : String) {
    write("<li class=\"listitem\">" + s + "</li>")
  }
  
  def listitem(content : => Unit) {
    write("<li class=\"listitem\">")
    content
    write("</li>")
  }
  
  def form(content : => Unit) {
    write("<form method=\"POST\">")
    write("<input type=\"hidden\" name=\"form-id\" value=\"" + formCounter + "\">")
    content
    write("</form>")
    formCounter += 1
  }

  def action(s : String)(a : => Unit) {
    write("<input type=\"submit\" name=\"action-" + actionCounter + "\" value=\"" + s + "\">")
    actionCounter += 1
  }
}