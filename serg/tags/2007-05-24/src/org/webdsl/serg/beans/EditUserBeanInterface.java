package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setUser(User user);

  public User getUser();

  public void setPerson0(Person person1);

  public String cancel();

  public String save();

  public void setNewPerson0(String p);

  public String getNewPerson0();

  public String selectPerson0();

  public Map<String, String> getPerson0List();

  public void initPerson0List();
}