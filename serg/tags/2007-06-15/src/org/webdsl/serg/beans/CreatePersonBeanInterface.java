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

  public void setBlog1(Blog blog11);

  public String cancel();

  public String save();

  public void setNewUser7(String p);

  public String getNewUser7();

  public void selectUser7(ValueChangeEvent event);

  public Map<String, String> getUser7List();

  public void initUser7List();

  public void setNewBlog10(String p);

  public String getNewBlog10();

  public void selectBlog10(ValueChangeEvent event);

  public Map<String, String> getBlog10List();

  public void initBlog10List();

  public List<Person> getPerson37List();

  public void initPerson37List();

  public List<ResearchProject> getProject30List();

  public void initProject30List();

  public Person getPerson();

  public void setPerson(Person person);
}