package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateConferenceBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson7(Person person165);

  public void addPerson7(Person person165);

  public String cancel();

  public String save();

  public void setNewPerson166(String p);

  public String getNewPerson166();

  public void selectPerson166(ValueChangeEvent event);

  public Map<String, String> getPerson166List();

  public void initPerson166List();

  public List<Person> getPerson71List();

  public void initPerson71List();

  public List<ResearchProject> getProject66List();

  public void initProject66List();

  public Conference getConference();

  public void setConference(Conference conference);
}