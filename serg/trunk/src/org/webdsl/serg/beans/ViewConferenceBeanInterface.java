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

  public String createNewPerson(Conference conference00, java.util.List<Person> editors0);

  public List<Person> getPerson84List();

  public void initPerson84List();

  public List<ResearchProject> getProject71List();

  public void initProject71List();
}