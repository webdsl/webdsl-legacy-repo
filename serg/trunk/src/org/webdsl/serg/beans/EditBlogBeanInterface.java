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

  public void setPerson2(Person person137);

  public void removeBlogEntry0(BlogEntry blogEntry5);

  public void addBlogEntry0(BlogEntry blogEntry5);

  public void removeCategory0(Category category5);

  public void addCategory0(Category category5);

  public String cancel();

  public String save();

  public void setNewPerson136(String p);

  public String getNewPerson136();

  public void selectPerson136(ValueChangeEvent event);

  public Map<String, String> getPerson136List();

  public void initPerson136List();

  public void setNewBlogEntry6(String p);

  public String getNewBlogEntry6();

  public void selectBlogEntry6(ValueChangeEvent event);

  public Map<String, String> getBlogEntry6List();

  public void initBlogEntry6List();

  public void setNewCategory6(String p);

  public String getNewCategory6();

  public void selectCategory6(ValueChangeEvent event);

  public Map<String, String> getCategory6List();

  public void initCategory6List();

  public List<Person> getPerson36List();

  public void initPerson36List();

  public List<ResearchProject> getProject31List();

  public void initProject31List();
}