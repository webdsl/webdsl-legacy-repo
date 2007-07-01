package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setUser(User user);

  public User getUser();

  public void setPerson1(Person person164);

  public String cancel();

  public String save();

  public void setNewPerson163(String p);

  public String getNewPerson163();

  public void selectPerson163(ValueChangeEvent event);

  public Map<String, String> getPerson163List();

  public void initPerson163List();

  public List<Person> getPerson30List();

  public void initPerson30List();

  public List<ResearchProject> getProject21List();

  public void initProject21List();
}