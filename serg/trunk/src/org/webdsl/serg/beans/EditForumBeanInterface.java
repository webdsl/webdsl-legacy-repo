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

  public void removeDiscussion0(Discussion discussion5);

  public void addDiscussion0(Discussion discussion5);

  public String cancel();

  public String save();

  public void setNewDiscussion6(String p);

  public String getNewDiscussion6();

  public void selectDiscussion6(ValueChangeEvent event);

  public Map<String, String> getDiscussion6List();

  public void initDiscussion6List();

  public List<Person> getPerson109List();

  public void initPerson109List();

  public List<ResearchProject> getProject109List();

  public void initProject109List();
}