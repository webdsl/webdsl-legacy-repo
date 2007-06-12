package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson7(Person person126);

  public String cancel();

  public String save();

  public void setNewPerson125(String p);

  public String getNewPerson125();

  public void selectPerson125(ValueChangeEvent event);

  public Map<String, String> getPerson125List();

  public void initPerson125List();

  public List<Person> getPerson46List();

  public void initPerson46List();

  public List<ResearchProject> getProject41List();

  public void initProject41List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}