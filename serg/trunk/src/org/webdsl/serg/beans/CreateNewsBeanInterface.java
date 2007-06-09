package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateNewsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public String cancel();

  public String save();

  public List<Person> getPerson1046List();

  public void initPerson1046List();

  public List<ResearchProject> getProject1146List();

  public void initProject1146List();

  public News getNews();

  public void setNews(News news);
}