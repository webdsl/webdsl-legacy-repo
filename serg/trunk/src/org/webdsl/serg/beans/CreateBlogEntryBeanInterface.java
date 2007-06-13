package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogEntryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlog3(Blog blog13);

  public void setCategory1(Category category12);

  public void removeBlogComment1(BlogComment blogComment7);

  public void addBlogComment1(BlogComment blogComment7);

  public String cancel();

  public String save();

  public void setNewBlog12(String p);

  public String getNewBlog12();

  public void selectBlog12(ValueChangeEvent event);

  public Map<String, String> getBlog12List();

  public void initBlog12List();

  public void setNewCategory11(String p);

  public String getNewCategory11();

  public void selectCategory11(ValueChangeEvent event);

  public Map<String, String> getCategory11List();

  public void initCategory11List();

  public void setNewBlogComment8(String p);

  public String getNewBlogComment8();

  public void selectBlogComment8(ValueChangeEvent event);

  public Map<String, String> getBlogComment8List();

  public void initBlogComment8List();

  public List<Person> getPerson40List();

  public void initPerson40List();

  public List<ResearchProject> getProject35List();

  public void initProject35List();

  public BlogEntry getBlogEntry();

  public void setBlogEntry(BlogEntry blogEntry);
}