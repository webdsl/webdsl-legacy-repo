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

  public void setBlog1(Blog blog9);

  public String cancel();

  public String save();

  public void setNewUser7(String p);

  public String getNewUser7();

  public void selectUser7(ValueChangeEvent event);

  public Map<String, String> getUser7List();

  public void initUser7List();

  public void setNewBlog8(String p);

  public String getNewBlog8();

  public void selectBlog8(ValueChangeEvent event);

  public Map<String, String> getBlog8List();

  public void initBlog8List();

  public List<Person> getPerson30List();

  public void initPerson30List();

  public List<ResearchProject> getProject28List();

  public void initProject28List();

  public Person getPerson();

  public void setPerson(Person person);
}