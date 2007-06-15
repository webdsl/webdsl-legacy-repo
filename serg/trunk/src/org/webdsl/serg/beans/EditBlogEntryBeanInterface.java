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

  public void setBlog2(Blog blog13);

  public void setCategory0(Category category11);

  public void removeBlogComment0(BlogComment blogComment6);

  public void addBlogComment0(BlogComment blogComment6);

  public String cancel();

  public String save();

  public void setNewBlog12(String p);

  public String getNewBlog12();

  public void selectBlog12(ValueChangeEvent event);

  public Map<String, String> getBlog12List();

  public void initBlog12List();

  public void setNewCategory10(String p);

  public String getNewCategory10();

  public void selectCategory10(ValueChangeEvent event);

  public Map<String, String> getCategory10List();

  public void initCategory10List();

  public void setNewBlogComment7(String p);

  public String getNewBlogComment7();

  public void selectBlogComment7(ValueChangeEvent event);

  public Map<String, String> getBlogComment7List();

  public void initBlogComment7List();

  public List<Person> getPerson45List();

  public void initPerson45List();

  public List<ResearchProject> getProject35List();

  public void initProject35List();
}