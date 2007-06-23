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

  public void setUser1(User user8);

  public void setBlog0(Blog blog9);

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

  public List<Person> getPerson36List();

  public void initPerson36List();

  public List<ResearchProject> getProject29List();

  public void initProject29List();
}