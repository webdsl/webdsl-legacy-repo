package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateArticleBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson11(Person person211);

  public void addPerson11(Person person211);

  public void addNewAuthor();

  public void removeResearchProject13(ResearchProject researchProject49);

  public void addResearchProject13(ResearchProject researchProject49);

  public void setJournal1(Journal journal8);

  public String cancel();

  public String save();

  public void setNewPerson212(String p);

  public String getNewPerson212();

  public void selectPerson212(ValueChangeEvent event);

  public Map<String, String> getPerson212List();

  public void initPerson212List();

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

  public List<Person> getPerson87List();

  public void initPerson87List();

  public List<ResearchProject> getProject74List();

  public void initProject74List();

  public Article getArticle();

  public void setArticle(Article article);

  public Person getNewAuthor7();

  public void setNewAuthor7(Person newAuthor7);
}