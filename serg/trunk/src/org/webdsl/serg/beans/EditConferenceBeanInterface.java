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

  public void removePerson8(Person person203);

  public void addPerson8(Person person203);

  public String cancel();

  public String save();

  public void setNewPerson204(String p);

  public String getNewPerson204();

  public void selectPerson204(ValueChangeEvent event);

  public Map<String, String> getPerson204List();

  public void initPerson204List();

  public List<Person> getPerson82List();

  public void initPerson82List();

  public List<ResearchProject> getProject69List();

  public void initProject69List();
}