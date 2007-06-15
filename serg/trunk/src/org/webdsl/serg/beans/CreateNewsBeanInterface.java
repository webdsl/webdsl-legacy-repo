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

  public List<Person> getPerson90List();

  public void initPerson90List();

  public List<ResearchProject> getProject85List();

  public void initProject85List();

  public News getNews();

  public void setNews(News news);
}