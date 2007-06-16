package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateDiscussionBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson12(Person person222);

  public void setForum1(Forum forum9);

  public void removeReply1(Reply reply10);

  public void addReply1(Reply reply10);

  public String cancel();

  public String save();

  public void setNewPerson221(String p);

  public String getNewPerson221();

  public void selectPerson221(ValueChangeEvent event);

  public Map<String, String> getPerson221List();

  public void initPerson221List();

  public void setNewForum8(String p);

  public String getNewForum8();

  public void selectForum8(ValueChangeEvent event);

  public Map<String, String> getForum8List();

  public void initForum8List();

  public void setNewReply11(String p);

  public String getNewReply11();

  public void selectReply11(ValueChangeEvent event);

  public Map<String, String> getReply11List();

  public void initReply11List();

  public List<Person> getPerson120List();

  public void initPerson120List();

  public List<ResearchProject> getProject114List();

  public void initProject114List();

  public Discussion getDiscussion();

  public void setDiscussion(Discussion discussion);
}