package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePostBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson16(Person person254);

  public void setDiscussion3(Discussion discussion18);

  public String cancel();

  public String save();

  public void setNewPerson253(String p);

  public String getNewPerson253();

  public void selectPerson253(ValueChangeEvent event);

  public Map<String, String> getPerson253List();

  public void initPerson253List();

  public void setNewDiscussion17(String p);

  public String getNewDiscussion17();

  public void selectDiscussion17(ValueChangeEvent event);

  public Map<String, String> getDiscussion17List();

  public void initDiscussion17List();

  public List<Person> getPerson134List();

  public void initPerson134List();

  public List<ResearchProject> getProject125List();

  public void initProject125List();

  public Post getPost();

  public void setPost(Post post);
}