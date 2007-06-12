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

  public void setPerson6(Person person124);

  public String cancel();

  public String save();

  public void setNewPerson123(String p);

  public String getNewPerson123();

  public void selectPerson123(ValueChangeEvent event);

  public Map<String, String> getPerson123List();

  public void initPerson123List();

  public List<Person> getPerson45List();

  public void initPerson45List();

  public List<ResearchProject> getProject40List();

  public void initProject40List();
}