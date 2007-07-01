package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson6(Person person174);

  public String cancel();

  public String save();

  public void setNewPerson173(String p);

  public String getNewPerson173();

  public void selectPerson173(ValueChangeEvent event);

  public Map<String, String> getPerson173List();

  public void initPerson173List();

  public List<Person> getPerson56List();

  public void initPerson56List();

  public List<ResearchProject> getProject43List();

  public void initProject43List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}