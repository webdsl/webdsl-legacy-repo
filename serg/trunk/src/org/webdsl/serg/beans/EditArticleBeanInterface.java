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

  public void removePerson10(Person person194);

  public void addPerson10(Person person194);

  public void addNewAuthor();

  public void removeResearchProject12(ResearchProject researchProject47);

  public void addResearchProject12(ResearchProject researchProject47);

  public void setJournal0(Journal journal6);

  public String cancel();

  public String save();

  public void setNewPerson195(String p);

  public String getNewPerson195();

  public void selectPerson195(ValueChangeEvent event);

  public Map<String, String> getPerson195List();

  public void initPerson195List();

  public void setNewResearchProject48(String p);

  public String getNewResearchProject48();

  public void selectResearchProject48(ValueChangeEvent event);

  public Map<String, String> getResearchProject48List();

  public void initResearchProject48List();

  public void setNewJournal5(String p);

  public String getNewJournal5();

  public void selectJournal5(ValueChangeEvent event);

  public Map<String, String> getJournal5List();

  public void initJournal5List();

  public List<Person> getPerson84List();

  public void initPerson84List();

  public List<ResearchProject> getProject73List();

  public void initProject73List();

  public Person getNewAuthor6();

  public void setNewAuthor6(Person newAuthor6);
}