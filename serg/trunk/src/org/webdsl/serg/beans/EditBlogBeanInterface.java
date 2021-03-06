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

  public void setPerson3(Person person168);

  public void removeBlogEntry0(BlogEntry blogEntry6);

  public void addBlogEntry0(BlogEntry blogEntry6);

  public void removeCategory0(Category category6);

  public void addCategory0(Category category6);

  public String cancel();

  public String save();

  public void setNewPerson167(String p);

  public String getNewPerson167();

  public void selectPerson167(ValueChangeEvent event);

  public Map<String, String> getPerson167List();

  public void initPerson167List();

  public void setNewBlogEntry7(String p);

  public String getNewBlogEntry7();

  public void selectBlogEntry7(ValueChangeEvent event);

  public Map<String, String> getBlogEntry7List();

  public void initBlogEntry7List();

  public void setNewCategory7(String p);

  public String getNewCategory7();

  public void selectCategory7(ValueChangeEvent event);

  public Map<String, String> getCategory7List();

  public void initCategory7List();

  public List<Person> getPerson45List();

  public void initPerson45List();

  public List<ResearchProject> getProject32List();

  public void initProject32List();
}