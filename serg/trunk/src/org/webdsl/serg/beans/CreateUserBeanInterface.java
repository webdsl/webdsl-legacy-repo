package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson1(Person person135);

  public String cancel();

  public String save();

  public void setNewPerson134(String p);

  public String getNewPerson134();

  public void selectPerson134(ValueChangeEvent event);

  public Map<String, String> getPerson134List();

  public void initPerson134List();

  public List<Person> getPerson22List();

  public void initPerson22List();

  public List<ResearchProject> getProject21List();

  public void initProject21List();

  public User getUser();

  public void setUser(User user);
}