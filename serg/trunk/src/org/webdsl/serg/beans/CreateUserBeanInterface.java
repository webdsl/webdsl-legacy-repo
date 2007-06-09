package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson1(Person person9);

  public String cancel();

  public String save();

  public void setNewPerson8(String p);

  public String getNewPerson8();

  public void selectPerson8(ValueChangeEvent event);

  public Map<String, String> getPerson8List();

  public void initPerson8List();

  public List<Person> getPerson1012List();

  public void initPerson1012List();

  public List<ResearchProject> getProject1112List();

  public void initProject1112List();

  public User getUser();

  public void setUser(User user);
}