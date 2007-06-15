package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllJournalBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeJournal(Journal journal4);

  public List<Person> getPerson81List();

  public void initPerson81List();

  public List<ResearchProject> getProject76List();

  public void initProject76List();

  public List<Journal> getJournal3List();

  public void initJournal3List();
}