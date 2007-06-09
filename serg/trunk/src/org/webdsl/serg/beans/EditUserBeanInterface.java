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

  public void setPerson0(Person person6);

  public String cancel();

  public String save();

  public void setNewPerson5(String p);

  public String getNewPerson5();

  public void selectPerson5(ValueChangeEvent event);

  public Map<String, String> getPerson5List();

  public void initPerson5List();

  public List<Person> getPerson107List();

  public void initPerson107List();

  public List<ResearchProject> getProject117List();

  public void initProject117List();
}