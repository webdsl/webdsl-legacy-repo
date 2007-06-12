package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogEntryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlog4(Blog blog15);

  public void setCategory1(Category category15);

  public void removeBlogComment1(BlogComment blogComment7);

  public void addBlogComment1(BlogComment blogComment7);

  public String cancel();

  public String save();

  public void setNewBlog14(String p);

  public String getNewBlog14();

  public void selectBlog14(ValueChangeEvent event);

  public Map<String, String> getBlog14List();

  public void initBlog14List();

  public void setNewCategory14(String p);

  public String getNewCategory14();

  public void selectCategory14(ValueChangeEvent event);

  public Map<String, String> getCategory14List();

  public void initCategory14List();

  public void setNewBlogComment8(String p);

  public String getNewBlogComment8();

  public void selectBlogComment8(ValueChangeEvent event);

  public Map<String, String> getBlogComment8List();

  public void initBlogComment8List();

  public List<Person> getPerson39List();

  public void initPerson39List();

  public List<ResearchProject> getProject34List();

  public void initProject34List();

  public BlogEntry getBlogEntry();

  public void setBlogEntry(BlogEntry blogEntry);
}