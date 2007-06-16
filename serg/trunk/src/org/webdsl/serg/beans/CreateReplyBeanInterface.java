package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateReplyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson14(Person person225);

  public void setDiscussion1(Discussion discussion14);

  public String cancel();

  public String save();

  public void setNewPerson224(String p);

  public String getNewPerson224();

  public void selectPerson224(ValueChangeEvent event);

  public Map<String, String> getPerson224List();

  public void initPerson224List();

  public void setNewDiscussion13(String p);

  public String getNewDiscussion13();

  public void selectDiscussion13(ValueChangeEvent event);

  public Map<String, String> getDiscussion13List();

  public void initDiscussion13List();

  public List<Person> getPerson122List();

  public void initPerson122List();

  public List<ResearchProject> getProject117List();

  public void initProject117List();

  public Reply getReply();

  public void setReply(Reply reply);
}