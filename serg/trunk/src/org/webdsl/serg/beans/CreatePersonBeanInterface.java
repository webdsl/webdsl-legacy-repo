package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePersonBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setUser1(User user6);

  public void setBlog1(Blog blog7);

  public String cancel();

  public String save();

  public void setNewUser5(String p);

  public String getNewUser5();

  public void selectUser5(ValueChangeEvent event);

  public Map<String, String> getUser5List();

  public void initUser5List();

  public void setNewBlog6(String p);

  public String getNewBlog6();

  public void selectBlog6(ValueChangeEvent event);

  public Map<String, String> getBlog6List();

  public void initBlog6List();

  public List<Person> getPerson1018List();

  public void initPerson1018List();

  public List<ResearchProject> getProject1118List();

  public void initProject1118List();

  public Person getPerson();

  public void setPerson(Person person);
}