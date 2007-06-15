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

  public void setPerson1(Person person134);

  public String cancel();

  public String save();

  public void setNewPerson133(String p);

  public String getNewPerson133();

  public void selectPerson133(ValueChangeEvent event);

  public Map<String, String> getPerson133List();

  public void initPerson133List();

  public List<Person> getPerson27List();

  public void initPerson27List();

  public List<ResearchProject> getProject21List();

  public void initProject21List();
}