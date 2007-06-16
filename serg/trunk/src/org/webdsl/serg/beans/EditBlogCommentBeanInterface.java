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

  public void setPerson5(Person person152);

  public String cancel();

  public String save();

  public void setNewPerson151(String p);

  public String getNewPerson151();

  public void selectPerson151(ValueChangeEvent event);

  public Map<String, String> getPerson151List();

  public void initPerson151List();

  public List<Person> getPerson52List();

  public void initPerson52List();

  public List<ResearchProject> getProject42List();

  public void initProject42List();
}