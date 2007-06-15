package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllArticleBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeArticle(Article article4);

  public List<Person> getPerson82List();

  public void initPerson82List();

  public List<ResearchProject> getProject72List();

  public void initProject72List();

  public List<Article> getArticle3List();

  public void initArticle3List();
}