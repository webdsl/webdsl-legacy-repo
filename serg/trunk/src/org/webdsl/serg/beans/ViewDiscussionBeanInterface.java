package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewDiscussionBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setDiscussion(Discussion discussion);

  public Discussion getDiscussion();

  public void delete(Reply reply2);

  public void setPerson0(Person person162);

  public void post();

  public void setNewPerson161(String p);

  public String getNewPerson161();

  public void selectPerson161(ValueChangeEvent event);

  public Map<String, String> getPerson161List();

  public void initPerson161List();

  public List<Person> getPerson28List();

  public void initPerson28List();

  public List<ResearchProject> getProject19List();

  public void initProject19List();

  public Reply getNewReply0();

  public void setNewReply0(Reply newReply0);
}