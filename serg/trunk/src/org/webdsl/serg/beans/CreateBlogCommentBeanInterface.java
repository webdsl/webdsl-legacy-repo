package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson6(Person person160);

  public String cancel();

  public String save();

  public void setNewPerson159(String p);

  public String getNewPerson159();

  public void selectPerson159(ValueChangeEvent event);

  public Map<String, String> getPerson159List();

  public void initPerson159List();

  public List<Person> getPerson54List();

  public void initPerson54List();

  public List<ResearchProject> getProject43List();

  public void initProject43List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}