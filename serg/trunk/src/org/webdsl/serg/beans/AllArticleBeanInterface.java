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

  public List<Person> getPerson89List();

  public void initPerson89List();

  public List<ResearchProject> getProject76List();

  public void initProject76List();

  public List<Article> getArticle3List();

  public void initArticle3List();
}