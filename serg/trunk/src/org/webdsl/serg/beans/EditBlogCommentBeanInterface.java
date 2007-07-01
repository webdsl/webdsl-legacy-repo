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

  public void setPerson5(Person person172);

  public String cancel();

  public String save();

  public void setNewPerson171(String p);

  public String getNewPerson171();

  public void selectPerson171(ValueChangeEvent event);

  public Map<String, String> getPerson171List();

  public void initPerson171List();

  public List<Person> getPerson55List();

  public void initPerson55List();

  public List<ResearchProject> getProject42List();

  public void initProject42List();
}