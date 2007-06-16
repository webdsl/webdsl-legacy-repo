package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditConferenceBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setConference(Conference conference);

  public Conference getConference();

  public void removePerson6(Person person179);

  public void addPerson6(Person person179);

  public String cancel();

  public String save();

  public void setNewPerson180(String p);

  public String getNewPerson180();

  public void selectPerson180(ValueChangeEvent event);

  public Map<String, String> getPerson180List();

  public void initPerson180List();

  public List<Person> getPerson75List();

  public void initPerson75List();

  public List<ResearchProject> getProject65List();

  public void initProject65List();
}