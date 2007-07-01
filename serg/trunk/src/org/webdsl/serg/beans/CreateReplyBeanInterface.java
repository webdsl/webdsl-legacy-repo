package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateReplyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson14(Person person250);

  public void setDiscussion1(Discussion discussion14);

  public String cancel();

  public String save();

  public void setNewPerson249(String p);

  public String getNewPerson249();

  public void selectPerson249(ValueChangeEvent event);

  public Map<String, String> getPerson249List();

  public void initPerson249List();

  public void setNewDiscussion13(String p);

  public String getNewDiscussion13();

  public void selectDiscussion13(ValueChangeEvent event);

  public Map<String, String> getDiscussion13List();

  public void initDiscussion13List();

  public List<Person> getPerson130List();

  public void initPerson130List();

  public List<ResearchProject> getProject121List();

  public void initProject121List();

  public Reply getReply();

  public void setReply(Reply reply);
}