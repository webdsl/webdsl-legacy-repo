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

  public void setPerson1(Person person144);

  public String cancel();

  public String save();

  public void setNewPerson143(String p);

  public String getNewPerson143();

  public void selectPerson143(ValueChangeEvent event);

  public Map<String, String> getPerson143List();

  public void initPerson143List();

  public List<Person> getPerson27List();

  public void initPerson27List();

  public List<ResearchProject> getProject21List();

  public void initProject21List();
}