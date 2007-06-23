package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditReplyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setReply(Reply reply);

  public Reply getReply();

  public void setPerson13(Person person234);

  public void setDiscussion0(Discussion discussion12);

  public String cancel();

  public String save();

  public void setNewPerson233(String p);

  public String getNewPerson233();

  public void selectPerson233(ValueChangeEvent event);

  public Map<String, String> getPerson233List();

  public void initPerson233List();

  public void setNewDiscussion11(String p);

  public String getNewDiscussion11();

  public void selectDiscussion11(ValueChangeEvent event);

  public Map<String, String> getDiscussion11List();

  public void initDiscussion11List();

  public List<Person> getPerson127List();

  public void initPerson127List();

  public List<ResearchProject> getProject120List();

  public void initProject120List();
}