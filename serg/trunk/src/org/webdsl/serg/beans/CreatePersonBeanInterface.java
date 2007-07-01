package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePersonBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setUser2(User user10);

  public void setBlog1(Blog blog11);

  public String cancel();

  public String save();

  public void setNewUser9(String p);

  public String getNewUser9();

  public void selectUser9(ValueChangeEvent event);

  public Map<String, String> getUser9List();

  public void initUser9List();

  public void setNewBlog10(String p);

  public String getNewBlog10();

  public void selectBlog10(ValueChangeEvent event);

  public Map<String, String> getBlog10List();

  public void initBlog10List();

  public List<Person> getPerson40List();

  public void initPerson40List();

  public List<ResearchProject> getProject30List();

  public void initProject30List();

  public Person getPerson();

  public void setPerson(Person person);
}