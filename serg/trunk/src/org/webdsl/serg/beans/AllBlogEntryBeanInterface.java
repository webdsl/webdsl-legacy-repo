package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllBlogEntryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeBlogEntry(BlogEntry blogEntry5);

  public List<Person> getPerson47List();

  public void initPerson47List();

  public List<ResearchProject> getProject37List();

  public void initProject37List();

  public List<BlogEntry> getBlogEntry4List();

  public void initBlogEntry4List();
}