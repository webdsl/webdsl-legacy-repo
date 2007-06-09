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

  public void setPerson6(Person person20);

  public String cancel();

  public String save();

  public void setNewPerson19(String p);

  public String getNewPerson19();

  public void selectPerson19(ValueChangeEvent event);

  public Map<String, String> getPerson19List();

  public void initPerson19List();

  public List<Person> getPerson1026List();

  public void initPerson1026List();

  public List<ResearchProject> getProject1126List();

  public void initProject1126List();
}