package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewNewsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setNews(News news);

  public News getNews();

  public List<Person> getPerson96List();

  public void initPerson96List();

  public List<ResearchProject> getProject85List();

  public void initProject85List();
}