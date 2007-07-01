package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateConferenceBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson9(Person person205);

  public void addPerson9(Person person205);

  public String cancel();

  public String save();

  public void setNewPerson206(String p);

  public String getNewPerson206();

  public void selectPerson206(ValueChangeEvent event);

  public Map<String, String> getPerson206List();

  public void initPerson206List();

  public List<Person> getPerson83List();

  public void initPerson83List();

  public List<ResearchProject> getProject70List();

  public void initProject70List();

  public Conference getConference();

  public void setConference(Conference conference);
}