package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogEntryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlog3(Blog blog15);

  public void setCategory1(Category category13);

  public void removeBlogComment1(BlogComment blogComment9);

  public void addBlogComment1(BlogComment blogComment9);

  public String cancel();

  public String save();

  public void setNewBlog14(String p);

  public String getNewBlog14();

  public void selectBlog14(ValueChangeEvent event);

  public Map<String, String> getBlog14List();

  public void initBlog14List();

  public void setNewCategory12(String p);

  public String getNewCategory12();

  public void selectCategory12(ValueChangeEvent event);

  public Map<String, String> getCategory12List();

  public void initCategory12List();

  public void setNewBlogComment10(String p);

  public String getNewBlogComment10();

  public void selectBlogComment10(ValueChangeEvent event);

  public Map<String, String> getBlogComment10List();

  public void initBlogComment10List();

  public List<Person> getPerson47List();

  public void initPerson47List();

  public List<ResearchProject> getProject36List();

  public void initProject36List();

  public BlogEntry getBlogEntry();

  public void setBlogEntry(BlogEntry blogEntry);
}