package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateArticleBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson9(Person person170);

  public void addPerson9(Person person170);

  public void addNewAuthor();

  public void removeResearchProject11(ResearchProject researchProject31);

  public void addResearchProject11(ResearchProject researchProject31);

  public void setJournal1(Journal journal8);

  public String cancel();

  public String save();

  public void setNewPerson171(String p);

  public String getNewPerson171();

  public void selectPerson171(ValueChangeEvent event);

  public Map<String, String> getPerson171List();

  public void initPerson171List();

  public void setNewResearchProject32(String p);

  public String getNewResearchProject32();

  public void selectResearchProject32(ValueChangeEvent event);

  public Map<String, String> getResearchProject32List();

  public void initResearchProject32List();

  public void setNewJournal7(String p);

  public String getNewJournal7();

  public void selectJournal7(ValueChangeEvent event);

  public Map<String, String> getJournal7List();

  public void initJournal7List();

  public List<Person> getPerson75List();

  public void initPerson75List();

  public List<ResearchProject> getProject70List();

  public void initProject70List();

  public Article getArticle();

  public void setArticle(Article article);

  public Person getNewAuthor7();

  public void setNewAuthor7(Person newAuthor7);
}