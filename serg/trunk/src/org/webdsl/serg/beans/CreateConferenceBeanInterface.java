package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateConferenceBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson7(Person person170);

  public void addPerson7(Person person170);

  public String cancel();

  public String save();

  public void setNewPerson171(String p);

  public String getNewPerson171();

  public void selectPerson171(ValueChangeEvent event);

  public Map<String, String> getPerson171List();

  public void initPerson171List();

  public List<Person> getPerson76List();

  public void initPerson76List();

  public List<ResearchProject> getProject66List();

  public void initProject66List();

  public Conference getConference();

  public void setConference(Conference conference);
}