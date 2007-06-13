package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson5(Person person121);

  public String cancel();

  public String save();

  public void setNewPerson120(String p);

  public String getNewPerson120();

  public void selectPerson120(ValueChangeEvent event);

  public Map<String, String> getPerson120List();

  public void initPerson120List();

  public List<Person> getPerson47List();

  public void initPerson47List();

  public List<ResearchProject> getProject42List();

  public void initProject42List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}