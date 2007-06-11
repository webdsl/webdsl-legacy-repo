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

  public List<Person> getPerson102List();

  public void initPerson102List();

  public List<ResearchProject> getProject112List();

  public void initProject112List();

  public java.util.List<ResearchProject> getProjects2();

  public void setProjects2(java.util.List<ResearchProject> projects2);

  public void initProjects2();
}