package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditDiscussionBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setDiscussion(Discussion discussion);

  public Discussion getDiscussion();

  public void setPerson11(Person person203);

  public void setForum0(Forum forum6);

  public void removeReply0(Reply reply8);

  public void addReply0(Reply reply8);

  public String cancel();

  public String save();

  public void setNewPerson202(String p);

  public String getNewPerson202();

  public void selectPerson202(ValueChangeEvent event);

  public Map<String, String> getPerson202List();

  public void initPerson202List();

  public void setNewForum5(String p);

  public String getNewForum5();

  public void selectForum5(ValueChangeEvent event);

  public Map<String, String> getForum5List();

  public void initForum5List();

  public void setNewReply9(String p);

  public String getNewReply9();

  public void selectReply9(ValueChangeEvent event);

  public Map<String, String> getReply9List();

  public void initReply9List();

  public List<Person> getPerson113List();

  public void initPerson113List();

  public List<ResearchProject> getProject113List();

  public void initProject113List();
}