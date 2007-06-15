package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewJournalBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setJournal(Journal journal);

  public Journal getJournal();

  public List<Person> getPerson80List();

  public void initPerson80List();

  public List<ResearchProject> getProject75List();

  public void initProject75List();
}