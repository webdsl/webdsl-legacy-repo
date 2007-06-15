package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllNewsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeNews(News news5);

  public List<Person> getPerson97List();

  public void initPerson97List();

  public List<ResearchProject> getProject87List();

  public void initProject87List();

  public List<News> getNews4List();

  public void initNews4List();
}