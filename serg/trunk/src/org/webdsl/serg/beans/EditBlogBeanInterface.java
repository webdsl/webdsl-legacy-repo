package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditBlogBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlog(Blog blog);

  public Blog getBlog();

  public void setPerson2(Person person115);

  public void removeBlogEntry0(BlogEntry blogEntry4);

  public void addBlogEntry0(BlogEntry blogEntry4);

  public void removeCategory0(Category category5);

  public void addCategory0(Category category5);

  public String cancel();

  public String save();

  public void setNewPerson114(String p);

  public String getNewPerson114();

  public void selectPerson114(ValueChangeEvent event);

  public Map<String, String> getPerson114List();

  public void initPerson114List();

  public void setNewBlogEntry5(String p);

  public String getNewBlogEntry5();

  public void selectBlogEntry5(ValueChangeEvent event);

  public Map<String, String> getBlogEntry5List();

  public void initBlogEntry5List();

  public void setNewCategory6(String p);

  public String getNewCategory6();

  public void selectCategory6(ValueChangeEvent event);

  public Map<String, String> getCategory6List();

  public void initCategory6List();

  public List<Person> getPerson36List();

  public void initPerson36List();

  public List<ResearchProject> getProject31List();

  public void initProject31List();
}