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

  public List<Person> getPerson103List();

  public void initPerson103List();

  public List<ResearchProject> getProject113List();

  public void initProject113List();

  public java.util.List<ResearchProject> getProjects3();

  public void setProjects3(java.util.List<ResearchProject> projects3);

  public void initProjects3();
}