package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson2(Person person136);

  public String cancel();

  public String save();

  public void setNewPerson135(String p);

  public String getNewPerson135();

  public void selectPerson135(ValueChangeEvent event);

  public Map<String, String> getPerson135List();

  public void initPerson135List();

  public List<Person> getPerson28List();

  public void initPerson28List();

  public List<ResearchProject> getProject22List();

  public void initProject22List();

  public User getUser();

  public void setUser(User user);
}