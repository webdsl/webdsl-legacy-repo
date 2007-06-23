package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateArticleBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson11(Person person196);

  public void addPerson11(Person person196);

  public void addNewAuthor();

  public void removeResearchProject13(ResearchProject researchProject49);

  public void addResearchProject13(ResearchProject researchProject49);

  public void setJournal1(Journal journal8);

  public String cancel();

  public String save();

  public void setNewPerson197(String p);

  public String getNewPerson197();

  public void selectPerson197(ValueChangeEvent event);

  public Map<String, String> getPerson197List();

  public void initPerson197List();

  public void setNewResearchProject50(String p);

  public String getNewResearchProject50();

  public void selectResearchProject50(ValueChangeEvent event);

  public Map<String, String> getResearchProject50List();

  public void initResearchProject50List();

  public void setNewJournal7(String p);

  public String getNewJournal7();

  public void selectJournal7(ValueChangeEvent event);

  public Map<String, String> getJournal7List();

  public void initJournal7List();

  public List<Person> getPerson85List();

  public void initPerson85List();

  public List<ResearchProject> getProject74List();

  public void initProject74List();

  public Article getArticle();

  public void setArticle(Article article);

  public Person getNewAuthor7();

  public void setNewAuthor7(Person newAuthor7);
}