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

  public void setPerson13(Person person207);

  public void setDiscussion0(Discussion discussion11);

  public String cancel();

  public String save();

  public void setNewPerson206(String p);

  public String getNewPerson206();

  public void selectPerson206(ValueChangeEvent event);

  public Map<String, String> getPerson206List();

  public void initPerson206List();

  public void setNewDiscussion10(String p);

  public String getNewDiscussion10();

  public void selectDiscussion10(ValueChangeEvent event);

  public Map<String, String> getDiscussion10List();

  public void initDiscussion10List();

  public List<Person> getPerson116List();

  public void initPerson116List();

  public List<ResearchProject> getProject116List();

  public void initProject116List();
}