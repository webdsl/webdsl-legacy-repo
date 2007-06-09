package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson7(Person person21);

  public String cancel();

  public String save();

  public void setNewPerson20(String p);

  public String getNewPerson20();

  public void selectPerson20(ValueChangeEvent event);

  public Map<String, String> getPerson20List();

  public void initPerson20List();

  public List<Person> getPerson1025List();

  public void initPerson1025List();

  public List<ResearchProject> getProject1125List();

  public void initProject1125List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}