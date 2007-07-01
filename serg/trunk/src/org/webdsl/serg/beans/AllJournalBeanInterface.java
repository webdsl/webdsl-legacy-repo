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

  public List<Person> getPerson93List();

  public void initPerson93List();

  public List<ResearchProject> getProject80List();

  public void initProject80List();

  public List<Journal> getJournal3List();

  public void initJournal3List();
}