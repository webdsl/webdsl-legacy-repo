package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson3(Person person117);

  public void removeBlogEntry1(BlogEntry blogEntry6);

  public void addBlogEntry1(BlogEntry blogEntry6);

  public void removeCategory1(Category category7);

  public void addCategory1(Category category7);

  public String cancel();

  public String save();

  public void setNewPerson116(String p);

  public String getNewPerson116();

  public void selectPerson116(ValueChangeEvent event);

  public Map<String, String> getPerson116List();

  public void initPerson116List();

  public void setNewBlogEntry7(String p);

  public String getNewBlogEntry7();

  public void selectBlogEntry7(ValueChangeEvent event);

  public Map<String, String> getBlogEntry7List();

  public void initBlogEntry7List();

  public void setNewCategory8(String p);

  public String getNewCategory8();

  public void selectCategory8(ValueChangeEvent event);

  public Map<String, String> getCategory8List();

  public void initCategory8List();

  public List<Person> getPerson37List();

  public void initPerson37List();

  public List<ResearchProject> getProject32List();

  public void initProject32List();

  public Blog getBlog();

  public void setBlog(Blog blog);
}