package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditArticleBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setArticle(Article article);

  public Article getArticle();

  public void removePerson8(Person person173);

  public void addPerson8(Person person173);

  public void addNewAuthor();

  public void removeResearchProject10(ResearchProject researchProject40);

  public void addResearchProject10(ResearchProject researchProject40);

  public void setJournal0(Journal journal6);

  public String cancel();

  public String save();

  public void setNewPerson174(String p);

  public String getNewPerson174();

  public void selectPerson174(ValueChangeEvent event);

  public Map<String, String> getPerson174List();

  public void initPerson174List();

  public void setNewResearchProject41(String p);

  public String getNewResearchProject41();

  public void selectResearchProject41(ValueChangeEvent event);

  public Map<String, String> getResearchProject41List();

  public void initResearchProject41List();

  public void setNewJournal5(String p);

  public String getNewJournal5();

  public void selectJournal5(ValueChangeEvent event);

  public Map<String, String> getJournal5List();

  public void initJournal5List();

  public List<Person> getPerson79List();

  public void initPerson79List();

  public List<ResearchProject> getProject69List();

  public void initProject69List();

  public Person getNewAuthor6();

  public void setNewAuthor6(Person newAuthor6);
}