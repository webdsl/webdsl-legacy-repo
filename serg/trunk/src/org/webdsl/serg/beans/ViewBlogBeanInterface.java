package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewBlogBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlog(Blog blog);

  public Blog getBlog();

  public String createNewBlogEntry();

  public String delete(BlogEntry entry3);

  public List<Person> getPerson15List();

  public void initPerson15List();

  public List<ResearchProject> getProject4List();

  public void initProject4List();

  public java.util.List<ResearchProject> getProjects2();

  public void setProjects2(java.util.List<ResearchProject> projects2);

  public void initProjects2();

  public java.util.List<BlogEntry> getEntries();

  public void setEntries(java.util.List<BlogEntry> entries);

  public void initEntries();
}