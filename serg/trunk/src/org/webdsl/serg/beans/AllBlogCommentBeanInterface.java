package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeBlogComment(BlogComment blogComment5);

  public List<Person> getPerson55List();

  public void initPerson55List();

  public List<ResearchProject> getProject45List();

  public void initProject45List();

  public List<BlogComment> getBlogComment4List();

  public void initBlogComment4List();
}