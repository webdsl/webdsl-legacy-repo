package org.webdsl.wiki.viewers;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletRequest;

import org.webdsl.wiki.domain.Topic;

public interface Viewer
{
  public String makeView(Topic topic);
  
  public void writeView(ServletRequest request, Topic topic, Writer out) throws IOException;
}
