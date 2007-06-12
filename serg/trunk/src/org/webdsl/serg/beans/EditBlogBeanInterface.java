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

  public void setPerson3(Person person118);

  public void removeBlogEntry0(BlogEntry blogEntry4);

  public void addBlogEntry0(BlogEntry blogEntry4);

  public void removeCategory0(Category category5);

  public void addCategory0(Category category5);

  public String cancel();

  public String save();

  public void setNewPerson117(String p);

  public String getNewPerson117();

  public void selectPerson117(ValueChangeEvent event);

  public Map<String, String> getPerson117List();

  public void initPerson117List();

  public void setNewBlogEntry5(String p);

  public String getNewBlogEntry5();

  public void selectBlogEntry5(ValueChangeEvent event);

  public Map<String, String> getBlogEntry5List();

  public void initBlogEntry5List();

  public void setNewCategory6(String p);

  public String getNewCategory6();

  public void selectCategory6(ValueChangeEvent event);

  public Map<String, String> getCategory6List();

  public void initCategory6List();

  public List<Person> getPerson35List();

  public void initPerson35List();

  public List<ResearchProject> getProject30List();

  public void initProject30List();
}