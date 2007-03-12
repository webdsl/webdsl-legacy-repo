

package org.webdsl.wiki.viewers;

import org.webdsl.wiki.domain.Topic;

public class TWikiViewer implements Viewer
{

  public String makeView(Topic topic)
  {
    String text = topic.getText();
    if (text == null)
      text = "";
    return text;
  }

}
