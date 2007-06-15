package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePostBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson16(Person person213);

  public void setDiscussion3(Discussion discussion17);

  public String cancel();

  public String save();

  public void setNewPerson212(String p);

  public String getNewPerson212();

  public void selectPerson212(ValueChangeEvent event);

  public Map<String, String> getPerson212List();

  public void initPerson212List();

  public void setNewDiscussion16(String p);

  public String getNewDiscussion16();

  public void selectDiscussion16(ValueChangeEvent event);

  public Map<String, String> getDiscussion16List();

  public void initDiscussion16List();

  public List<Person> getPerson121List();

  public void initPerson121List();

  public List<ResearchProject> getProject121List();

  public void initProject121List();

  public Post getPost();

  public void setPost(Post post);
}