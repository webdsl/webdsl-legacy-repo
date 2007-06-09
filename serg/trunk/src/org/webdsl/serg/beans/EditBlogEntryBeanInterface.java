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

  public void setBlog3(Blog blog11);

  public void setCategory0(Category category11);

  public void removeBlogComment0(BlogComment blogComment3);

  public void addBlogComment0(BlogComment blogComment3);

  public String cancel();

  public String save();

  public void setNewBlog10(String p);

  public String getNewBlog10();

  public void selectBlog10(ValueChangeEvent event);

  public Map<String, String> getBlog10List();

  public void initBlog10List();

  public void setNewCategory10(String p);

  public String getNewCategory10();

  public void selectCategory10(ValueChangeEvent event);

  public Map<String, String> getCategory10List();

  public void initCategory10List();

  public void setNewBlogComment4(String p);

  public String getNewBlogComment4();

  public void selectBlogComment4(ValueChangeEvent event);

  public Map<String, String> getBlogComment4List();

  public void initBlogComment4List();

  public List<Person> getPerson1019List();

  public void initPerson1019List();

  public List<ResearchProject> getProject1119List();

  public void initProject1119List();
}