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

  public List<Person> getPerson90List();

  public void initPerson90List();

  public List<ResearchProject> getProject79List();

  public void initProject79List();
}