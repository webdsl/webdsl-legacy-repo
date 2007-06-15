package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePersonBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setUser1(User user8);

  public void setBlog1(Blog blog10);

  public String cancel();

  public String save();

  public void setNewUser7(String p);

  public String getNewUser7();

  public void selectUser7(ValueChangeEvent event);

  public Map<String, String> getUser7List();

  public void initUser7List();

  public void setNewBlog9(String p);

  public String getNewBlog9();

  public void selectBlog9(ValueChangeEvent event);

  public Map<String, String> getBlog9List();

  public void initBlog9List();

  public List<Person> getPerson32List();

  public void initPerson32List();

  public List<ResearchProject> getProject30List();

  public void initProject30List();

  public Person getPerson();

  public void setPerson(Person person);
}