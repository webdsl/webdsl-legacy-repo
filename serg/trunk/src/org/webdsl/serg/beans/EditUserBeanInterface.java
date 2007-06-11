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

  public void setPerson0(Person person14);

  public String cancel();

  public String save();

  public void setNewPerson13(String p);

  public String getNewPerson13();

  public void selectPerson13(ValueChangeEvent event);

  public Map<String, String> getPerson13List();

  public void initPerson13List();

  public List<Person> getPerson1011List();

  public void initPerson1011List();

  public List<ResearchProject> getProject1111List();

  public void initProject1111List();
}