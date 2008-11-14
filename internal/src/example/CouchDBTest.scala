package example

//import com.fourspaces.couchdb._
import java.io.IOException
import java.net._
import java.io._
import org.json._

class Connection(url : String) {
  
  def apply(db : String) = new Database(db, this)
  
  def request(path : String, requestMethod : String, body : String) : String = {
    val urlConn = new URL(url + path).openConnection.asInstanceOf[HttpURLConnection]
    urlConn.setConnectTimeout(20000); // Any point in this?
    urlConn.setDoOutput(true)
    urlConn.setRequestMethod(requestMethod)
    if(requestMethod != "GET") {
      urlConn.setDoInput(true)
      urlConn.setUseCaches(false)
      urlConn.setRequestProperty("Content-Type","application/json")
      val printout = new DataOutputStream(urlConn.getOutputStream())
      printout.writeBytes(body)
      printout.flush()
      printout.close()
    }
    val response = new StringBuilder()
    val in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	var	line = in.readLine();
	while (line != null) {
	  response.append(line + "\n")
      line = in.readLine();
	}
    in.close()
    return response.toString
  }
}

class Database(name : String, conn : Connection) {
  def apply(id : String) = load(id)

  def load(id : String) : Document = {
    var doc = new Document()
    doc.id = id
    val response = conn.request("/" + name + "/" + id, "GET", null)
    doc.doc = new JSONObject(response)
    return doc
  }
  
  def save(doc : Document) {
    var response = ""
    if(doc.id == null) {
      response = conn.request("/" + name + "/", "POST", doc.toString)
    } else {
      response = conn.request("/" + name + "/" + doc.id, "PUT", doc.toString)
    }
    val respObj = new JSONObject(response)
    doc.id = respObj.getString("id")
  }
}

class Document(var id : String) {
  var doc = new JSONObject()
  
  def this() = this(null)
  
  def put(prop : String, value : Any) {
    doc.put(prop, value)
  }
  
  def getString(prop : String) = doc.getString(prop)
  def getInt(prop : String) = doc.getInt(prop)
  def getMap(prop : String) = doc.getJSONObject(prop)
  
  override def toString = doc.toString
}

object CouchDBTest extends Application {
  val c = new Connection("http://localhost:5984")
  val db = c("webdsl")
  val doc = db("zef")
  doc.put("age", doc.getInt("age") + 1)
  db.save(doc)
  println(doc.getInt("age"))
  /*val doc = new Document("zef")
  doc.put("name", "Zef Hemel")
  doc.put("age", 25)
  db.save(doc)*/
  //val doc2 = db.load("zef")
  //println("Loaded: " + doc2.getInt("age"))
  //println(c.request("/webdsl", "GET", ""))
  //println(c.request("/webdsl", "POST", """{"name": "Zef Hemel"}"""))
  /*val s = new Session("localhost", 5984)
  val db = s.getDatabase("webdsl")
  val zef = new Document()
  zef.put("name", "Zef Hemel")
  zef.put("age", 25)
  //db.saveDocument(zef)
  
  //val result = ; // same as db.view("_all_dbs");
  for (partDoc <- db.getAllDocuments.getResults.toArray(new Array[Document](0))) {
    val doc = db.getDocument(partDoc.getString("id"))
    println(doc)
  }*/
}
