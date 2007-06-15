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

  public void setPerson5(Person person142);

  public String cancel();

  public String save();

  public void setNewPerson141(String p);

  public String getNewPerson141();

  public void selectPerson141(ValueChangeEvent event);

  public Map<String, String> getPerson141List();

  public void initPerson141List();

  public List<Person> getPerson52List();

  public void initPerson52List();

  public List<ResearchProject> getProject42List();

  public void initProject42List();
}