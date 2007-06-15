package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllConferenceBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeConference(Conference conference4);

  public List<Person> getPerson73List();

  public void initPerson73List();

  public List<ResearchProject> getProject68List();

  public void initProject68List();

  public List<Conference> getConference3List();

  public void initConference3List();
}