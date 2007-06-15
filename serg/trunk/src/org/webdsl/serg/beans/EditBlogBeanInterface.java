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

  public void setPerson3(Person person133);

  public void removeBlogEntry0(BlogEntry blogEntry6);

  public void addBlogEntry0(BlogEntry blogEntry6);

  public void removeCategory0(Category category5);

  public void addCategory0(Category category5);

  public String cancel();

  public String save();

  public void setNewPerson132(String p);

  public String getNewPerson132();

  public void selectPerson132(ValueChangeEvent event);

  public Map<String, String> getPerson132List();

  public void initPerson132List();

  public void setNewBlogEntry7(String p);

  public String getNewBlogEntry7();

  public void selectBlogEntry7(ValueChangeEvent event);

  public Map<String, String> getBlogEntry7List();

  public void initBlogEntry7List();

  public void setNewCategory6(String p);

  public String getNewCategory6();

  public void selectCategory6(ValueChangeEvent event);

  public Map<String, String> getCategory6List();

  public void initCategory6List();

  public List<Person> getPerson37List();

  public void initPerson37List();

  public List<ResearchProject> getProject32List();

  public void initProject32List();
}