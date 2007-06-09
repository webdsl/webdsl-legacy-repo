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

  public List<ResearchProject> getPr1List();

  public void initPr1List();

  public List<Person> getPerson102List();

  public void initPerson102List();

  public List<ResearchProject> getProject112List();

  public void initProject112List();
}