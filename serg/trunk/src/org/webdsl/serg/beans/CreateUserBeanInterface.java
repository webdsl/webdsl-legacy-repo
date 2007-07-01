package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson2(Person person166);

  public String cancel();

  public String save();

  public void setNewPerson165(String p);

  public String getNewPerson165();

  public void selectPerson165(ValueChangeEvent event);

  public Map<String, String> getPerson165List();

  public void initPerson165List();

  public List<Person> getPerson31List();

  public void initPerson31List();

  public List<ResearchProject> getProject22List();

  public void initProject22List();

  public User getUser();

  public void setUser(User user);
}