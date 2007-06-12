package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson1(Person person114);

  public String cancel();

  public String save();

  public void setNewPerson113(String p);

  public String getNewPerson113();

  public void selectPerson113(ValueChangeEvent event);

  public Map<String, String> getPerson113List();

  public void initPerson113List();

  public List<Person> getPerson21List();

  public void initPerson21List();

  public List<ResearchProject> getProject20List();

  public void initProject20List();

  public User getUser();

  public void setUser(User user);
}