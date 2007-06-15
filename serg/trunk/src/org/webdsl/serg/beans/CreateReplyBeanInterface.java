package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateReplyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson14(Person person209);

  public void setDiscussion1(Discussion discussion13);

  public String cancel();

  public String save();

  public void setNewPerson208(String p);

  public String getNewPerson208();

  public void selectPerson208(ValueChangeEvent event);

  public Map<String, String> getPerson208List();

  public void initPerson208List();

  public void setNewDiscussion12(String p);

  public String getNewDiscussion12();

  public void selectDiscussion12(ValueChangeEvent event);

  public Map<String, String> getDiscussion12List();

  public void initDiscussion12List();

  public List<Person> getPerson117List();

  public void initPerson117List();

  public List<ResearchProject> getProject117List();

  public void initProject117List();

  public Reply getReply();

  public void setReply(Reply reply);
}