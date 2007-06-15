package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditNewsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setNews(News news);

  public News getNews();

  public String cancel();

  public String save();

  public List<Person> getPerson94List();

  public void initPerson94List();

  public List<ResearchProject> getProject83List();

  public void initProject83List();
}