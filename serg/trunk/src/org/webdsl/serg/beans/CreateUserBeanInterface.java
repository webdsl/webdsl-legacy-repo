package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson2(Person person131);

  public String cancel();

  public String save();

  public void setNewPerson130(String p);

  public String getNewPerson130();

  public void selectPerson130(ValueChangeEvent event);

  public Map<String, String> getPerson130List();

  public void initPerson130List();

  public List<Person> getPerson23List();

  public void initPerson23List();

  public List<ResearchProject> getProject22List();

  public void initProject22List();

  public User getUser();

  public void setUser(User user);
}