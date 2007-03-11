

package org.webdsl.wiki.viewers;

import org.webdsl.wiki.domain.Topic;

public class TWikiViewer implements Viewer
{

  public String makeView(Topic topic)
  {
    return topic.getText();
  }

}
