package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewConferenceBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setConference(Conference conference);

  public Conference getConference();

  public List<Person> getPerson72List();

  public void initPerson72List();

  public List<ResearchProject> getProject67List();

  public void initProject67List();
}