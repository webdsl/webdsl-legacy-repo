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

  public List<Person> getPerson111List();

  public void initPerson111List();

  public List<ResearchProject> getProject111List();

  public void initProject111List();
}