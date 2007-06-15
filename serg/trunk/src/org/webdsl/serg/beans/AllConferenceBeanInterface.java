package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllConferenceBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeConference(Conference conference5);

  public List<Person> getPerson78List();

  public void initPerson78List();

  public List<ResearchProject> getProject68List();

  public void initProject68List();

  public List<Conference> getConference4List();

  public void initConference4List();
}