package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewArticleBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setArticle(Article article);

  public Article getArticle();

  public String createNewPerson(Publication publication03, java.util.List<Person> authors4);

  public String createNewResearchProject(Publication publication110, java.util.Set<ResearchProject> projects9);

  public List<Person> getPerson86List();

  public void initPerson86List();

  public List<ResearchProject> getProject75List();

  public void initProject75List();
}