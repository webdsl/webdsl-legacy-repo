package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson7(Person person28);

  public String cancel();

  public String save();

  public void setNewPerson27(String p);

  public String getNewPerson27();

  public void selectPerson27(ValueChangeEvent event);

  public Map<String, String> getPerson27List();

  public void initPerson27List();

  public List<Person> getPerson1027List();

  public void initPerson1027List();

  public List<ResearchProject> getProject1127List();

  public void initProject1127List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}