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

  public void removePerson6(Person person178);

  public void addPerson6(Person person178);

  public String cancel();

  public String save();

  public void setNewPerson179(String p);

  public String getNewPerson179();

  public void selectPerson179(ValueChangeEvent event);

  public Map<String, String> getPerson179List();

  public void initPerson179List();

  public List<Person> getPerson75List();

  public void initPerson75List();

  public List<ResearchProject> getProject65List();

  public void initProject65List();
}