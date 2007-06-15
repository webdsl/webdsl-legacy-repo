package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPersonBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson(Person person);

  public Person getPerson();

  public void setUser0(User user6);

  public void setBlog0(Blog blog8);

  public String cancel();

  public String save();

  public void setNewUser5(String p);

  public String getNewUser5();

  public void selectUser5(ValueChangeEvent event);

  public Map<String, String> getUser5List();

  public void initUser5List();

  public void setNewBlog7(String p);

  public String getNewBlog7();

  public void selectBlog7(ValueChangeEvent event);

  public Map<String, String> getBlog7List();

  public void initBlog7List();

  public List<Person> getPerson30List();

  public void initPerson30List();

  public List<ResearchProject> getProject29List();

  public void initProject29List();
}