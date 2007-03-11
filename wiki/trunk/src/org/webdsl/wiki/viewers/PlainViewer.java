

package org.webdsl.wiki.viewers;

import org.webdsl.wiki.domain.Topic;

public class PlainViewer implements Viewer
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
