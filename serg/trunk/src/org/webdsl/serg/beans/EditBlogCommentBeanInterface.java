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

  public void setPerson4(Person person141);

  public String cancel();

  public String save();

  public void setNewPerson140(String p);

  public String getNewPerson140();

  public void selectPerson140(ValueChangeEvent event);

  public Map<String, String> getPerson140List();

  public void initPerson140List();

  public List<Person> getPerson46List();

  public void initPerson46List();

  public List<ResearchProject> getProject41List();

  public void initProject41List();
}