package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateJournalBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public String cancel();

  public String save();

  public List<Person> getPerson79List();

  public void initPerson79List();

  public List<ResearchProject> getProject74List();

  public void initProject74List();

  public Journal getJournal();

  public void setJournal(Journal journal);
}