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

  public void setBlog2(Blog blog12);

  public void setCategory0(Category category10);

  public void removeBlogComment0(BlogComment blogComment5);

  public void addBlogComment0(BlogComment blogComment5);

  public String cancel();

  public String save();

  public void setNewBlog11(String p);

  public String getNewBlog11();

  public void selectBlog11(ValueChangeEvent event);

  public Map<String, String> getBlog11List();

  public void initBlog11List();

  public void setNewCategory9(String p);

  public String getNewCategory9();

  public void selectCategory9(ValueChangeEvent event);

  public Map<String, String> getCategory9List();

  public void initCategory9List();

  public void setNewBlogComment6(String p);

  public String getNewBlogComment6();

  public void selectBlogComment6(ValueChangeEvent event);

  public Map<String, String> getBlogComment6List();

  public void initBlogComment6List();

  public List<Person> getPerson39List();

  public void initPerson39List();

  public List<ResearchProject> getProject34List();

  public void initProject34List();
}