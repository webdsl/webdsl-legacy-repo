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

  public void removePerson8(Person person168);

  public void addPerson8(Person person168);

  public void addNewAuthor();

  public void removeResearchProject10(ResearchProject researchProject29);

  public void addResearchProject10(ResearchProject researchProject29);

  public void setJournal0(Journal journal6);

  public String cancel();

  public String save();

  public void setNewPerson169(String p);

  public String getNewPerson169();

  public void selectPerson169(ValueChangeEvent event);

  public Map<String, String> getPerson169List();

  public void initPerson169List();

  public void setNewResearchProject30(String p);

  public String getNewResearchProject30();

  public void selectResearchProject30(ValueChangeEvent event);

  public Map<String, String> getResearchProject30List();

  public void initResearchProject30List();

  public void setNewJournal5(String p);

  public String getNewJournal5();

  public void selectJournal5(ValueChangeEvent event);

  public Map<String, String> getJournal5List();

  public void initJournal5List();

  public List<Person> getPerson74List();

  public void initPerson74List();

  public List<ResearchProject> getProject69List();

  public void initProject69List();

  public Person getNewAuthor6();

  public void setNewAuthor6(Person newAuthor6);
}