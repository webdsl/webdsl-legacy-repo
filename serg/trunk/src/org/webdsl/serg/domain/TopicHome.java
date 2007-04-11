package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("topicHome") public class TopicHome extends EntityHome<Topic> 
{ 
  @Factory("topic") public Topic initTopic()
  { 
    return getInstance();
  }
}