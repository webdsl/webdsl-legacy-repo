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

  public void removeBlogComment1(BlogComment blogComment8);

  public void addBlogComment1(BlogComment blogComment8);

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

  public void setNewBlogComment9(String p);

  public String getNewBlogComment9();

  public void selectBlogComment9(ValueChangeEvent event);

  public Map<String, String> getBlogComment9List();

  public void initBlogComment9List();

  public List<Person> getPerson46List();

  public void initPerson46List();

  public List<ResearchProject> getProject36List();

  public void initProject36List();

  public BlogEntry getBlogEntry();

  public void setBlogEntry(BlogEntry blogEntry);
}