package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson1(Person person113);

  public String cancel();

  public String save();

  public void setNewPerson112(String p);

  public String getNewPerson112();

  public void selectPerson112(ValueChangeEvent event);

  public Map<String, String> getPerson112List();

  public void initPerson112List();

  public List<Person> getPerson22List();

  public void initPerson22List();

  public List<ResearchProject> getProject21List();

  public void initProject21List();

  public User getUser();

  public void setUser(User user);
}