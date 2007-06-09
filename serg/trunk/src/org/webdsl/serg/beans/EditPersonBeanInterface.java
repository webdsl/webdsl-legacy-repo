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

  public void setUser0(User user4);

  public void setBlog0(Blog blog5);

  public String cancel();

  public String save();

  public void setNewUser3(String p);

  public String getNewUser3();

  public void selectUser3(ValueChangeEvent event);

  public Map<String, String> getUser3List();

  public void initUser3List();

  public void setNewBlog4(String p);

  public String getNewBlog4();

  public void selectBlog4(ValueChangeEvent event);

  public Map<String, String> getBlog4List();

  public void initBlog4List();

  public List<Person> getPerson1013List();

  public void initPerson1013List();

  public List<ResearchProject> getProject1113List();

  public void initProject1113List();
}