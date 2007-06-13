package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlogComment(BlogComment blogComment);

  public BlogComment getBlogComment();

  public List<Person> getPerson48List();

  public void initPerson48List();

  public List<ResearchProject> getProject43List();

  public void initProject43List();
}