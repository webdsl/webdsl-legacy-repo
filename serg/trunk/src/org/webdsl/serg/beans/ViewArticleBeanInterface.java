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

  public String createNewPerson(Publication publication03, java.util.List<Person> authors3);

  public String createNewResearchProject(Publication publication110, java.util.Set<ResearchProject> projects8);

  public List<Person> getPerson81List();

  public void initPerson81List();

  public List<ResearchProject> getProject71List();

  public void initProject71List();
}