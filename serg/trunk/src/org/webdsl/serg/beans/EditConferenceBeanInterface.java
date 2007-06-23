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

  public void removePerson8(Person person189);

  public void addPerson8(Person person189);

  public String cancel();

  public String save();

  public void setNewPerson190(String p);

  public String getNewPerson190();

  public void selectPerson190(ValueChangeEvent event);

  public Map<String, String> getPerson190List();

  public void initPerson190List();

  public List<Person> getPerson80List();

  public void initPerson80List();

  public List<ResearchProject> getProject69List();

  public void initProject69List();
}