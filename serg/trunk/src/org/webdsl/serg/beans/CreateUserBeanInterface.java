package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson1(Person person16);

  public String cancel();

  public String save();

  public void setNewPerson15(String p);

  public String getNewPerson15();

  public void selectPerson15(ValueChangeEvent event);

  public Map<String, String> getPerson15List();

  public void initPerson15List();

  public List<Person> getPerson1012List();

  public void initPerson1012List();

  public List<ResearchProject> getProject1112List();

  public void initProject1112List();

  public User getUser();

  public void setUser(User user);
}