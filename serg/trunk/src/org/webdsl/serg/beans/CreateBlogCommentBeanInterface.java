package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson6(Person person155);

  public String cancel();

  public String save();

  public void setNewPerson154(String p);

  public String getNewPerson154();

  public void selectPerson154(ValueChangeEvent event);

  public Map<String, String> getPerson154List();

  public void initPerson154List();

  public List<Person> getPerson53List();

  public void initPerson53List();

  public List<ResearchProject> getProject43List();

  public void initProject43List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}