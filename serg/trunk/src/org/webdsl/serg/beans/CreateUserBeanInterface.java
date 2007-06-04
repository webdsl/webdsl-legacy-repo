package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson0(Person person1);

  public String cancel();

  public String save();

  public void setNewPerson0(String p);

  public String getNewPerson0();

  public String selectPerson0();

  public Map<String, String> getPerson0List();

  public void initPerson0List();

  public List<Person> getPerson10List();

  public void initPerson10List();

  public User getUser();

  public void setUser(User user);
}