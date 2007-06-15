package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateDiscussionBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson12(Person person205);

  public void setForum1(Forum forum8);

  public void removeReply1(Reply reply10);

  public void addReply1(Reply reply10);

  public String cancel();

  public String save();

  public void setNewPerson204(String p);

  public String getNewPerson204();

  public void selectPerson204(ValueChangeEvent event);

  public Map<String, String> getPerson204List();

  public void initPerson204List();

  public void setNewForum7(String p);

  public String getNewForum7();

  public void selectForum7(ValueChangeEvent event);

  public Map<String, String> getForum7List();

  public void initForum7List();

  public void setNewReply11(String p);

  public String getNewReply11();

  public void selectReply11(ValueChangeEvent event);

  public Map<String, String> getReply11List();

  public void initReply11List();

  public List<Person> getPerson114List();

  public void initPerson114List();

  public List<ResearchProject> getProject114List();

  public void initProject114List();

  public Discussion getDiscussion();

  public void setDiscussion(Discussion discussion);
}