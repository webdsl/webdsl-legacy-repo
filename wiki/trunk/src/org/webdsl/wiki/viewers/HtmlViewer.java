

package org.webdsl.wiki.viewers;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletRequest;

import org.webdsl.wiki.domain.Topic;

public class HtmlViewer implements Viewer
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
    out.write(makeView(topic));
  }
}
