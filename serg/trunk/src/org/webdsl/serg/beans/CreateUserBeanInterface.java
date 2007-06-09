package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson1(Person person8);

  public String cancel();

  public String save();

  public void setNewPerson7(String p);

  public String getNewPerson7();

  public void selectPerson7(ValueChangeEvent event);

  public Map<String, String> getPerson7List();

  public void initPerson7List();

  public List<Person> getPerson1010List();

  public void initPerson1010List();

  public List<ResearchProject> getProject1110List();

  public void initProject1110List();

  public User getUser();

  public void setUser(User user);
}