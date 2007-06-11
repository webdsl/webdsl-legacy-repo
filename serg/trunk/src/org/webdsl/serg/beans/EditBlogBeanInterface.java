package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditBlogBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlog(Blog blog);

  public Blog getBlog();

  public void setPerson3(Person person20);

  public void removeBlogEntry0(BlogEntry blogEntry2);

  public void addBlogEntry0(BlogEntry blogEntry2);

  public void removeCategory0(Category category3);

  public void addCategory0(Category category3);

  public String cancel();

  public String save();

  public void setNewPerson19(String p);

  public String getNewPerson19();

  public void selectPerson19(ValueChangeEvent event);

  public Map<String, String> getPerson19List();

  public void initPerson19List();

  public void setNewBlogEntry3(String p);

  public String getNewBlogEntry3();

  public void selectBlogEntry3(ValueChangeEvent event);

  public Map<String, String> getBlogEntry3List();

  public void initBlogEntry3List();

  public void setNewCategory4(String p);

  public String getNewCategory4();

  public void selectCategory4(ValueChangeEvent event);

  public Map<String, String> getCategory4List();

  public void initCategory4List();

  public List<Person> getPerson1019List();

  public void initPerson1019List();

  public List<ResearchProject> getProject1119List();

  public void initProject1119List();
}