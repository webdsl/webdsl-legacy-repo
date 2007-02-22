package xml;

import java.io.*;
import java.util.*;

import topics.*;
import users.*;

public class ToXML
{

  public static void export(String file, Topic topic)
	throws FileNotFoundException, UnsupportedEncodingException, IOException
  {
     OutputStream fout = new FileOutputStream(file);
     OutputStream bout = new BufferedOutputStream(fout);
     OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");
     ToXML toxml = new ToXML(out);
     toxml.export(topic);
     out.flush();
     out.close();
  }

  public ToXML(OutputStreamWriter out)
  {
    this.out = out;
    indent = 0;
  }

  OutputStreamWriter out;

  int indent;

  public void export(Topic topic) throws IOException
  {
    export(topic, null);
  }

  public void export(Topic topic, String topicname) throws IOException
  {
    if(topic == null) return;

    open("topic", (topicname == null ? "" : " name=\"" + topicname + "\""));
    text("title",    topic.getTitle());
    text("mimetype", topic.getMimetype());
    text("text",     topic.getText());
    
    open("subtopics");
    Map subtopics = topic.getSubtopics();
    for(Object name : subtopics.keySet())
    {
      export((Topic)subtopics.get(name), (String)name);
    }
    close("subtopics");
    close("topic");
  }

  private void open(String tag) throws IOException
  {
    open(tag, ""); 
  }

  private void open(String tag, String atts) throws IOException
  {
     write("<" + tag + atts + ">\n");
     indent();
  }

  private void close(String tag) throws IOException
  {
     dedent();
     write("</" + tag + ">\n");
  }

  private void text(String tag, String text) throws IOException
  {
    write("<" + tag + "><![CDATA[" + text + "]]></" + tag + ">\n");
  }

  private void write(String x) throws IOException
  {
    for (int i = 0; i < indent; i++) out.write(" ");
    out.write(x);
  }

  private void indent()
  {
    indent = indent + 2;
  }

  private void dedent()
  {
    indent = indent - 2;
  }  

}
