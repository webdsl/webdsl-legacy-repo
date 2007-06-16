package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson6(Person person154);

  public String cancel();

  public String save();

  public void setNewPerson153(String p);

  public String getNewPerson153();

  public void selectPerson153(ValueChangeEvent event);

  public Map<String, String> getPerson153List();

  public void initPerson153List();

  public List<Person> getPerson53List();

  public void initPerson53List();

  public List<ResearchProject> getProject43List();

  public void initProject43List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}