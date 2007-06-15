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

  public void removeBlogComment0(BlogComment blogComment6);

  public void addBlogComment0(BlogComment blogComment6);

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

  public void setNewBlogComment7(String p);

  public String getNewBlogComment7();

  public void selectBlogComment7(ValueChangeEvent event);

  public Map<String, String> getBlogComment7List();

  public void initBlogComment7List();

  public List<Person> getPerson40List();

  public void initPerson40List();

  public List<ResearchProject> getProject35List();

  public void initProject35List();
}