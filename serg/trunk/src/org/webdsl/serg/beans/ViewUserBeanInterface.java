package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setUser(User user);

  public User getUser();

  public List<Person> getPerson109List();

  public void initPerson109List();

  public List<ResearchProject> getProject119List();

  public void initProject119List();
}