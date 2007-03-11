package org.webdsl.wiki.viewers;

import org.webdsl.wiki.domain.Topic;

public interface Viewer
{
  public String makeView(Topic topic);
}
