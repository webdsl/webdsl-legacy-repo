package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateForumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeDiscussion1(Discussion discussion8);

  public void addDiscussion1(Discussion discussion8);

  public String cancel();

  public String save();

  public void setNewDiscussion9(String p);

  public String getNewDiscussion9();

  public void selectDiscussion9(ValueChangeEvent event);

  public Map<String, String> getDiscussion9List();

  public void initDiscussion9List();

  public List<Person> getPerson123List();

  public void initPerson123List();

  public List<ResearchProject> getProject114List();

  public void initProject114List();

  public Forum getForum();

  public void setForum(Forum forum);
}