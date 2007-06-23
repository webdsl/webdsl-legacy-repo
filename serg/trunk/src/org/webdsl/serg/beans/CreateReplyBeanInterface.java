package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateReplyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson14(Person person236);

  public void setDiscussion1(Discussion discussion14);

  public String cancel();

  public String save();

  public void setNewPerson235(String p);

  public String getNewPerson235();

  public void selectPerson235(ValueChangeEvent event);

  public Map<String, String> getPerson235List();

  public void initPerson235List();

  public void setNewDiscussion13(String p);

  public String getNewDiscussion13();

  public void selectDiscussion13(ValueChangeEvent event);

  public Map<String, String> getDiscussion13List();

  public void initDiscussion13List();

  public List<Person> getPerson128List();

  public void initPerson128List();

  public List<ResearchProject> getProject121List();

  public void initProject121List();

  public Reply getReply();

  public void setReply(Reply reply);
}