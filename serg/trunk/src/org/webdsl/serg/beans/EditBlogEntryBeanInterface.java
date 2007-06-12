package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditBlogEntryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlogEntry(BlogEntry blogEntry);

  public BlogEntry getBlogEntry();

  public void setBlog3(Blog blog13);

  public void setCategory0(Category category13);

  public void removeBlogComment0(BlogComment blogComment5);

  public void addBlogComment0(BlogComment blogComment5);

  public String cancel();

  public String save();

  public void setNewBlog12(String p);

  public String getNewBlog12();

  public void selectBlog12(ValueChangeEvent event);

  public Map<String, String> getBlog12List();

  public void initBlog12List();

  public void setNewCategory12(String p);

  public String getNewCategory12();

  public void selectCategory12(ValueChangeEvent event);

  public Map<String, String> getCategory12List();

  public void initCategory12List();

  public void setNewBlogComment6(String p);

  public String getNewBlogComment6();

  public void selectBlogComment6(ValueChangeEvent event);

  public Map<String, String> getBlogComment6List();

  public void initBlogComment6List();

  public List<Person> getPerson38List();

  public void initPerson38List();

  public List<ResearchProject> getProject33List();

  public void initProject33List();
}