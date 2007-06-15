package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllReplyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeReply(Reply reply7);

  public List<Person> getPerson124List();

  public void initPerson124List();

  public List<ResearchProject> getProject119List();

  public void initProject119List();

  public List<Reply> getReply6List();

  public void initReply6List();
}