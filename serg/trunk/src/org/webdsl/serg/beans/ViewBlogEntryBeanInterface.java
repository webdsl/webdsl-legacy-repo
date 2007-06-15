package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewBlogEntryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setEntry(BlogEntry entry);

  public BlogEntry getEntry();

  public List<Person> getPerson14List();

  public void initPerson14List();

  public List<ResearchProject> getProject5List();

  public void initProject5List();

  public java.util.List<ResearchProject> getProjects3();

  public void setProjects3(java.util.List<ResearchProject> projects3);

  public void initProjects3();
}