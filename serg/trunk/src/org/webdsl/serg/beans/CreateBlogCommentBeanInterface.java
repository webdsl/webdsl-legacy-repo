package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson5(Person person143);

  public String cancel();

  public String save();

  public void setNewPerson142(String p);

  public String getNewPerson142();

  public void selectPerson142(ValueChangeEvent event);

  public Map<String, String> getPerson142List();

  public void initPerson142List();

  public List<Person> getPerson47List();

  public void initPerson47List();

  public List<ResearchProject> getProject42List();

  public void initProject42List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}