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

  public void setPerson13(Person person248);

  public void setDiscussion0(Discussion discussion12);

  public String cancel();

  public String save();

  public void setNewPerson247(String p);

  public String getNewPerson247();

  public void selectPerson247(ValueChangeEvent event);

  public Map<String, String> getPerson247List();

  public void initPerson247List();

  public void setNewDiscussion11(String p);

  public String getNewDiscussion11();

  public void selectDiscussion11(ValueChangeEvent event);

  public Map<String, String> getDiscussion11List();

  public void initDiscussion11List();

  public List<Person> getPerson129List();

  public void initPerson129List();

  public List<ResearchProject> getProject120List();

  public void initProject120List();
}