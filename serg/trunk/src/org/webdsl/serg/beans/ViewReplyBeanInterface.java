package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewReplyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setReply(Reply reply);

  public Reply getReply();

  public List<Person> getPerson131List();

  public void initPerson131List();

  public List<ResearchProject> getProject122List();

  public void initProject122List();
}