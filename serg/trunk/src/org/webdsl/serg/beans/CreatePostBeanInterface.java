package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePostBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson16(Person person230);

  public void setDiscussion3(Discussion discussion18);

  public String cancel();

  public String save();

  public void setNewPerson229(String p);

  public String getNewPerson229();

  public void selectPerson229(ValueChangeEvent event);

  public Map<String, String> getPerson229List();

  public void initPerson229List();

  public void setNewDiscussion17(String p);

  public String getNewDiscussion17();

  public void selectDiscussion17(ValueChangeEvent event);

  public Map<String, String> getDiscussion17List();

  public void initDiscussion17List();

  public List<Person> getPerson127List();

  public void initPerson127List();

  public List<ResearchProject> getProject121List();

  public void initProject121List();

  public Post getPost();

  public void setPost(Post post);
}