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

  public void setPerson1(Person person150);

  public String cancel();

  public String save();

  public void setNewPerson149(String p);

  public String getNewPerson149();

  public void selectPerson149(ValueChangeEvent event);

  public Map<String, String> getPerson149List();

  public void initPerson149List();

  public List<Person> getPerson28List();

  public void initPerson28List();

  public List<ResearchProject> getProject21List();

  public void initProject21List();
}