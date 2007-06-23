package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson2(Person person152);

  public String cancel();

  public String save();

  public void setNewPerson151(String p);

  public String getNewPerson151();

  public void selectPerson151(ValueChangeEvent event);

  public Map<String, String> getPerson151List();

  public void initPerson151List();

  public List<Person> getPerson29List();

  public void initPerson29List();

  public List<ResearchProject> getProject22List();

  public void initProject22List();

  public User getUser();

  public void setUser(User user);
}