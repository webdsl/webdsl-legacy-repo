package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson4(Person person15);

  public void removeBlogEntry1(BlogEntry blogEntry4);

  public void addBlogEntry1(BlogEntry blogEntry4);

  public void removeCategory1(Category category5);

  public void addCategory1(Category category5);

  public String cancel();

  public String save();

  public void setNewPerson14(String p);

  public String getNewPerson14();

  public void selectPerson14(ValueChangeEvent event);

  public Map<String, String> getPerson14List();

  public void initPerson14List();

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

  public List<Person> getPerson1018List();

  public void initPerson1018List();

  public List<ResearchProject> getProject1118List();

  public void initProject1118List();

  public Blog getBlog();

  public void setBlog(Blog blog);
}