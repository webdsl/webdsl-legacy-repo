package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewForumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setForum(Forum forum);

  public Forum getForum();

  public String createNewDiscussion(Forum forum00, java.util.List<Discussion> discussions0);

  public List<Person> getPerson122List();

  public void initPerson122List();

  public List<ResearchProject> getProject115List();

  public void initProject115List();
}