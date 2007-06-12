package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson4(Person person120);

  public void removeBlogEntry1(BlogEntry blogEntry6);

  public void addBlogEntry1(BlogEntry blogEntry6);

  public void removeCategory1(Category category7);

  public void addCategory1(Category category7);

  public String cancel();

  public String save();

  public void setNewPerson119(String p);

  public String getNewPerson119();

  public void selectPerson119(ValueChangeEvent event);

  public Map<String, String> getPerson119List();

  public void initPerson119List();

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

  public List<Person> getPerson36List();

  public void initPerson36List();

  public List<ResearchProject> getProject31List();

  public void initProject31List();

  public Blog getBlog();

  public void setBlog(Blog blog);
}