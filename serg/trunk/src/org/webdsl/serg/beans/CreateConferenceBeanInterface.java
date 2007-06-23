package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateConferenceBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson9(Person person191);

  public void addPerson9(Person person191);

  public String cancel();

  public String save();

  public void setNewPerson192(String p);

  public String getNewPerson192();

  public void selectPerson192(ValueChangeEvent event);

  public Map<String, String> getPerson192List();

  public void initPerson192List();

  public List<Person> getPerson81List();

  public void initPerson81List();

  public List<ResearchProject> getProject70List();

  public void initProject70List();

  public Conference getConference();

  public void setConference(Conference conference);
}