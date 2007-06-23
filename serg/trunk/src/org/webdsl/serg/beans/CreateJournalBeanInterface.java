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

  public List<Person> getPerson89List();

  public void initPerson89List();

  public List<ResearchProject> getProject78List();

  public void initProject78List();

  public Journal getJournal();

  public void setJournal(Journal journal);
}