package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllBlogBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeBlog(Blog blog7);

  public List<Person> getPerson44List();

  public void initPerson44List();

  public List<ResearchProject> getProject34List();

  public void initProject34List();

  public List<Blog> getBlog6List();

  public void initBlog6List();
}