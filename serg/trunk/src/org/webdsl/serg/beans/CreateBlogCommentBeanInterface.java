package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateBlogCommentBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson7(Person person22);

  public String cancel();

  public String save();

  public void setNewPerson21(String p);

  public String getNewPerson21();

  public void selectPerson21(ValueChangeEvent event);

  public Map<String, String> getPerson21List();

  public void initPerson21List();

  public List<Person> getPerson1027List();

  public void initPerson1027List();

  public List<ResearchProject> getProject1127List();

  public void initProject1127List();

  public BlogComment getBlogComment();

  public void setBlogComment(BlogComment blogComment);
}