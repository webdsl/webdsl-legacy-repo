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

  public void setPerson0(Person person148);

  public void post();

  public void setNewPerson147(String p);

  public String getNewPerson147();

  public void selectPerson147(ValueChangeEvent event);

  public Map<String, String> getPerson147List();

  public void initPerson147List();

  public List<Person> getPerson26List();

  public void initPerson26List();

  public List<ResearchProject> getProject19List();

  public void initProject19List();

  public Reply getNewReply0();

  public void setNewReply0(Reply newReply0);
}