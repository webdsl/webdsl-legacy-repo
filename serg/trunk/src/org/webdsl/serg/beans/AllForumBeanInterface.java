package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllForumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeForum(Forum forum5);

  public List<Person> getPerson125List();

  public void initPerson125List();

  public List<ResearchProject> getProject116List();

  public void initProject116List();

  public List<Forum> getForum4List();

  public void initForum4List();
}