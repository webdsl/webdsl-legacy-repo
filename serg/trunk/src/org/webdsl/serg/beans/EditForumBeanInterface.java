package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditForumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setForum(Forum forum);

  public Forum getForum();

  public void removeDiscussion0(Discussion discussion6);

  public void addDiscussion0(Discussion discussion6);

  public String cancel();

  public String save();

  public void setNewDiscussion7(String p);

  public String getNewDiscussion7();

  public void selectDiscussion7(ValueChangeEvent event);

  public Map<String, String> getDiscussion7List();

  public void initDiscussion7List();

  public List<Person> getPerson122List();

  public void initPerson122List();

  public List<ResearchProject> getProject113List();

  public void initProject113List();
}