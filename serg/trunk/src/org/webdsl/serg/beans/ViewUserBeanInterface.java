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

  public List<Person> getPerson1013List();

  public void initPerson1013List();

  public List<ResearchProject> getProject1113List();

  public void initProject1113List();
}