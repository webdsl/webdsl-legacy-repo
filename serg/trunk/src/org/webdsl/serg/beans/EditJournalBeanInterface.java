package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditJournalBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setJournal(Journal journal);

  public Journal getJournal();

  public String cancel();

  public String save();

  public List<Person> getPerson78List();

  public void initPerson78List();

  public List<ResearchProject> getProject73List();

  public void initProject73List();
}