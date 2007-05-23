package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPersonBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson(Person person);

  public Person getPerson();

  public void setUser0(User user1);

  public String cancel();

  public String save();

  public void setNewUser0(String p);

  public String getNewUser0();

  public String selectUser0();

  public Map<String, String> getUser0List();

  public void initUser0List();
}