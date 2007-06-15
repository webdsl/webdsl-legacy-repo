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

  public void setPerson5(Person person137);

  public String cancel();

  public String save();

  public void setNewPerson136(String p);

  public String getNewPerson136();

  public void selectPerson136(ValueChangeEvent event);

  public Map<String, String> getPerson136List();

  public void initPerson136List();

  public List<Person> getPerson47List();

  public void initPerson47List();

  public List<ResearchProject> getProject42List();

  public void initProject42List();
}