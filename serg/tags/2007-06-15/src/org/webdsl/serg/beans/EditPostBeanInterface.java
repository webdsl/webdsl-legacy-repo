package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPostBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPost(Post post);

  public Post getPost();

  public void setPerson15(Person person217);

  public void setDiscussion2(Discussion discussion16);

  public String cancel();

  public String save();

  public void setNewPerson216(String p);

  public String getNewPerson216();

  public void selectPerson216(ValueChangeEvent event);

  public Map<String, String> getPerson216List();

  public void initPerson216List();

  public void setNewDiscussion15(String p);

  public String getNewDiscussion15();

  public void selectDiscussion15(ValueChangeEvent event);

  public Map<String, String> getDiscussion15List();

  public void initDiscussion15List();

  public List<Person> getPerson125List();

  public void initPerson125List();

  public List<ResearchProject> getProject120List();

  public void initProject120List();
}