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

  public void setPerson11(Person person208);

  public void setForum0(Forum forum7);

  public void removeReply0(Reply reply8);

  public void addReply0(Reply reply8);

  public String cancel();

  public String save();

  public void setNewPerson207(String p);

  public String getNewPerson207();

  public void selectPerson207(ValueChangeEvent event);

  public Map<String, String> getPerson207List();

  public void initPerson207List();

  public void setNewForum6(String p);

  public String getNewForum6();

  public void selectForum6(ValueChangeEvent event);

  public Map<String, String> getForum6List();

  public void initForum6List();

  public void setNewReply9(String p);

  public String getNewReply9();

  public void selectReply9(ValueChangeEvent event);

  public Map<String, String> getReply9List();

  public void initReply9List();

  public List<Person> getPerson118List();

  public void initPerson118List();

  public List<ResearchProject> getProject113List();

  public void initProject113List();
}