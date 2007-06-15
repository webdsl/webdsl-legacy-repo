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

  public void setPerson13(Person person213);

  public void setDiscussion0(Discussion discussion12);

  public String cancel();

  public String save();

  public void setNewPerson212(String p);

  public String getNewPerson212();

  public void selectPerson212(ValueChangeEvent event);

  public Map<String, String> getPerson212List();

  public void initPerson212List();

  public void setNewDiscussion11(String p);

  public String getNewDiscussion11();

  public void selectDiscussion11(ValueChangeEvent event);

  public Map<String, String> getDiscussion11List();

  public void initDiscussion11List();

  public List<Person> getPerson121List();

  public void initPerson121List();

  public List<ResearchProject> getProject116List();

  public void initProject116List();
}