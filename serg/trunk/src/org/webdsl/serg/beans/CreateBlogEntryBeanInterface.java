package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogEntryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlog3(Blog blog14);

  public void setCategory1(Category category12);

  public void removeBlogComment1(BlogComment blogComment8);

  public void addBlogComment1(BlogComment blogComment8);

  public String cancel();

  public String save();

  public void setNewBlog13(String p);

  public String getNewBlog13();

  public void selectBlog13(ValueChangeEvent event);

  public Map<String, String> getBlog13List();

  public void initBlog13List();

  public void setNewCategory11(String p);

  public String getNewCategory11();

  public void selectCategory11(ValueChangeEvent event);

  public Map<String, String> getCategory11List();

  public void initCategory11List();

  public void setNewBlogComment9(String p);

  public String getNewBlogComment9();

  public void selectBlogComment9(ValueChangeEvent event);

  public Map<String, String> getBlogComment9List();

  public void initBlogComment9List();

  public List<Person> getPerson41List();

  public void initPerson41List();

  public List<ResearchProject> getProject36List();

  public void initProject36List();

  public BlogEntry getBlogEntry();

  public void setBlogEntry(BlogEntry blogEntry);
}