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

  public void setPerson1(Person person145);

  public String cancel();

  public String save();

  public void setNewPerson144(String p);

  public String getNewPerson144();

  public void selectPerson144(ValueChangeEvent event);

  public Map<String, String> getPerson144List();

  public void initPerson144List();

  public List<Person> getPerson27List();

  public void initPerson27List();

  public List<ResearchProject> getProject21List();

  public void initProject21List();
}