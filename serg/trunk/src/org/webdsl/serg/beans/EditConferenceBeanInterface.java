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

  public void removePerson6(Person person163);

  public void addPerson6(Person person163);

  public String cancel();

  public String save();

  public void setNewPerson164(String p);

  public String getNewPerson164();

  public void selectPerson164(ValueChangeEvent event);

  public Map<String, String> getPerson164List();

  public void initPerson164List();

  public List<Person> getPerson70List();

  public void initPerson70List();

  public List<ResearchProject> getProject65List();

  public void initProject65List();
}