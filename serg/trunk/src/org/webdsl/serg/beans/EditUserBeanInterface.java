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

  public void setPerson0(Person person112);

  public String cancel();

  public String save();

  public void setNewPerson111(String p);

  public String getNewPerson111();

  public void selectPerson111(ValueChangeEvent event);

  public Map<String, String> getPerson111List();

  public void initPerson111List();

  public List<Person> getPerson20List();

  public void initPerson20List();

  public List<ResearchProject> getProject19List();

  public void initProject19List();
}