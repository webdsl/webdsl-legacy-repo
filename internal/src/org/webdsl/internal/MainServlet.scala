package org.webdsl.internal

import javax.servlet._
import javax.servlet.http._
import java.io._
import scala.collection.mutable.HashMap

abstract class MainServlet extends HttpServlet {
  var pageMap = HashMap[String, String]()
  def pages : List[Page]
  
  override def init {
    for(p <- pages) {
      pageMap(p.name) = p.getClass.getName
      //println("Adding: " + p.name)
    }
    println("Loaded pages: " + pageMap)
  }
  
  override def doPost(req : HttpServletRequest, res : HttpServletResponse) = doGet(req, res)
  
  override def doGet( req:HttpServletRequest, res:HttpServletResponse ) {
    res.setContentType("text/html");
    val parts = req.getPathInfo.split("/")
    var page = if(parts.length == 0) "home" else parts(1)
    //p.printWriter.println(page)
    var p : Page = null

    if(parts.length >= 2) {
        val pargs = parts.subArray(2, parts.length)
	    val c = Class.forName(pageMap(page))
	    val args = new Array[AnyRef](pargs.length)
	    for(constructor <- c.getConstructors) {
	      var matches = true
	      var i = 0
	      for(argType <- constructor.getParameterTypes) {
	        if(argType == classOf[String]) {
	          args(i) = pargs(i)
	        } else if(argType == classOf[Int]) {
	          args(i) = int2Integer(pargs(i).toInt)
	        } else {
	          matches = false
	        }
	        i += 1
	      }
	      if(matches) {
	        p = constructor.newInstance(args :_*).asInstanceOf[Page]
	      }
	    }
    } else {
      p = Class.forName(pageMap("home")).newInstance.asInstanceOf[Page]
    }
    if(p != null) {
        p.init(res.getWriter, req, res)
        p.resetCounters
        p.doDatabind
        p.resetCounters
        p.doAction
        p.resetCounters
        p.doRender
    } else {
      res.getWriter.println("Sorry, could not load page")
    }
  }
}
