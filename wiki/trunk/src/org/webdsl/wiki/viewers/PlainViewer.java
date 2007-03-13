

package org.webdsl.wiki.viewers;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletRequest;

import org.webdsl.wiki.domain.Topic;

public class PlainViewer extends HtmlViewer
{

  public String makeView(Topic topic)
  {
    String text = topic.getText();
    if (text == null)
      text = "";
    else
      text = "<pre>" + org.webdsl.wiki.utilities.EscapeChars.escape(text) + "</pre>";
    return text;
  }

}
