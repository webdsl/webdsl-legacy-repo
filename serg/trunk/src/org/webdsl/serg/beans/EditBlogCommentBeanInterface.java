package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setBlogComment(BlogComment blogComment);

  public BlogComment getBlogComment();

  public void setPerson5(Person person158);

  public String cancel();

  public String save();

  public void setNewPerson157(String p);

  public String getNewPerson157();

  public void selectPerson157(ValueChangeEvent event);

  public Map<String, String> getPerson157List();

  public void initPerson157List();

  public List<Person> getPerson53List();

  public void initPerson53List();

  public List<ResearchProject> getProject42List();

  public void initProject42List();
}