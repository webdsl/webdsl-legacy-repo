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

  public List<Person> getPerson76List();

  public void initPerson76List();

  public List<ResearchProject> getProject71List();

  public void initProject71List();
}