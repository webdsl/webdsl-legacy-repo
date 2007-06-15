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

  public void setPerson1(Person person129);

  public String cancel();

  public String save();

  public void setNewPerson128(String p);

  public String getNewPerson128();

  public void selectPerson128(ValueChangeEvent event);

  public Map<String, String> getPerson128List();

  public void initPerson128List();

  public List<Person> getPerson22List();

  public void initPerson22List();

  public List<ResearchProject> getProject21List();

  public void initProject21List();
}