

package org.webdsl.wiki.viewers;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletRequest;

import org.webdsl.wiki.domain.Topic;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TWikiViewer implements Viewer
{

  public String makeView(Topic topic)
  {
    String text = topic.getText();
    if (text == null)
      text = "";
    
    return text;
  }
  
  public void writeView(ServletRequest request, Topic topic, Writer out) throws IOException
  {
    ContentHandler handler = new WikiContentHandler(request, out);
    WikiParser parser = new WikiParser(topic.getText(), handler); 
    try {
      out.write("start parsing document");
      parser.parse();
      out.write("done parsing document");
    } catch (SAXException e) {
      throw new RuntimeException(e);
    }
  }
}
