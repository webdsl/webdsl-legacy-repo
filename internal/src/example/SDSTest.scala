package example

import java.io.IOException
import java.net._
import java.io._

class Authority(name : String) {
  
}
object SDSTest extends Application {
  val authorityId = "zef"
  val containerId = "Test";
  val entityTemplate = 
    <Book xmlns:s='http://schemas.microsoft.com/sitka/2008/03/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:x='http://www.w3.org/2001/XMLSchema'>
    <s:Id>%s</s:Id>
    <title xsi:type='x:string'>%s</title>
    <summary xsi:type='x:string'>%s</summary>
    <isbn xsi:type='x:string'>%s</isbn>
    <author xsi:type='x:string'>%s</author>
    <publisher xsi:type='x:string'>%s</publisher>
    </Book>

  val containerUri = String.format("https://%s.data.database.windows.net/v1/%s", authorityId, containerId)

	def doPost(url : String, variables : Map[String, String]) : URLConnection = {
		val urlConn = new URL(url).openConnection
		urlConn.setConnectTimeout(20000); // Any point in this?
		urlConn.setDoInput(true)
		urlConn.setDoOutput(true)
		urlConn.setUseCaches(false)
		urlConn.setRequestProperty("Content-Type","application/x-ssds+xml")
		val printout = new DataOutputStream(urlConn
				.getOutputStream());
		val content = new StringBuilder();
		var first = true;
		for (key <- variables.keySet) {
			if (!first) {
				content.append('&');
			} else {
				first = false;
			}
			content.append(key);
			content.append('=');
			content.append(URLEncoder.encode(variables(key), "UTF-8"));
		}
		printout.writeBytes(content.toString());
		printout.flush();
		printout.close();
		return urlConn;
	}

}
