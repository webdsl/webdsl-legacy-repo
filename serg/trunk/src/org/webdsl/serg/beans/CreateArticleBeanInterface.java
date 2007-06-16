package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateArticleBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson9(Person person185);

  public void addPerson9(Person person185);

  public void addNewAuthor();

  public void removeResearchProject11(ResearchProject researchProject42);

  public void addResearchProject11(ResearchProject researchProject42);

  public void setJournal1(Journal journal8);

  public String cancel();

  public String save();

  public void setNewPerson186(String p);

  public String getNewPerson186();

  public void selectPerson186(ValueChangeEvent event);

  public Map<String, String> getPerson186List();

  public void initPerson186List();

  public void setNewResearchProject43(String p);

  public String getNewResearchProject43();

  public void selectResearchProject43(ValueChangeEvent event);

  public Map<String, String> getResearchProject43List();

  public void initResearchProject43List();

  public void setNewJournal7(String p);

  public String getNewJournal7();

  public void selectJournal7(ValueChangeEvent event);

  public Map<String, String> getJournal7List();

  public void initJournal7List();

  public List<Person> getPerson80List();

  public void initPerson80List();

  public List<ResearchProject> getProject70List();

  public void initProject70List();

  public Article getArticle();

  public void setArticle(Article article);

  public Person getNewAuthor7();

  public void setNewAuthor7(Person newAuthor7);
}