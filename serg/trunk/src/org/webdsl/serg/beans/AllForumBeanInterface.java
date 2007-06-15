package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllForumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeForum(Forum forum4);

  public List<Person> getPerson112List();

  public void initPerson112List();

  public List<ResearchProject> getProject112List();

  public void initProject112List();

  public List<Forum> getForum3List();

  public void initForum3List();
}