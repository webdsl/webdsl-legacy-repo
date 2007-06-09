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

  public void setPerson6(Person person19);

  public String cancel();

  public String save();

  public void setNewPerson18(String p);

  public String getNewPerson18();

  public void selectPerson18(ValueChangeEvent event);

  public Map<String, String> getPerson18List();

  public void initPerson18List();

  public List<Person> getPerson1024List();

  public void initPerson1024List();

  public List<ResearchProject> getProject1124List();

  public void initProject1124List();
}