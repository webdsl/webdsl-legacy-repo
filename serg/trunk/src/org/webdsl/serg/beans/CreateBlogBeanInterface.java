package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson4(Person person150);

  public void removeBlogEntry1(BlogEntry blogEntry8);

  public void addBlogEntry1(BlogEntry blogEntry8);

  public void removeCategory1(Category category8);

  public void addCategory1(Category category8);

  public String cancel();

  public String save();

  public void setNewPerson149(String p);

  public String getNewPerson149();

  public void selectPerson149(ValueChangeEvent event);

  public Map<String, String> getPerson149List();

  public void initPerson149List();

  public void setNewBlogEntry9(String p);

  public String getNewBlogEntry9();

  public void selectBlogEntry9(ValueChangeEvent event);

  public Map<String, String> getBlogEntry9List();

  public void initBlogEntry9List();

  public void setNewCategory9(String p);

  public String getNewCategory9();

  public void selectCategory9(ValueChangeEvent event);

  public Map<String, String> getCategory9List();

  public void initCategory9List();

  public List<Person> getPerson43List();

  public void initPerson43List();

  public List<ResearchProject> getProject33List();

  public void initProject33List();

  public Blog getBlog();

  public void setBlog(Blog blog);
}