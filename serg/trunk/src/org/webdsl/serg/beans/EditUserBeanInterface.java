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

  public void setPerson0(Person person133);

  public String cancel();

  public String save();

  public void setNewPerson132(String p);

  public String getNewPerson132();

  public void selectPerson132(ValueChangeEvent event);

  public Map<String, String> getPerson132List();

  public void initPerson132List();

  public List<Person> getPerson21List();

  public void initPerson21List();

  public List<ResearchProject> getProject20List();

  public void initProject20List();
}