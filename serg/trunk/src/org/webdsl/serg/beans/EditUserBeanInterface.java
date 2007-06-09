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

  public void setPerson0(Person person7);

  public String cancel();

  public String save();

  public void setNewPerson6(String p);

  public String getNewPerson6();

  public void selectPerson6(ValueChangeEvent event);

  public Map<String, String> getPerson6List();

  public void initPerson6List();

  public List<Person> getPerson1011List();

  public void initPerson1011List();

  public List<ResearchProject> getProject1111List();

  public void initProject1111List();
}