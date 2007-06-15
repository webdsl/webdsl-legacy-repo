package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson6(Person person139);

  public String cancel();

  public String save();

  public void setNewPerson138(String p);

  public String getNewPerson138();

  public void selectPerson138(ValueChangeEvent event);

  public Map<String, String> getPerson138List();

  public void initPerson138List();

  public List<Person> getPerson48List();

  public void initPerson48List();

  public List<ResearchProject> getProject43List();

  public void initProject43List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}