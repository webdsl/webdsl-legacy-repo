package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface PersonPublicationsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setP(Person p);

  public Person getP();

  public List<ResearchProject> getPr3List();

  public void initPr3List();

  public List<Person> getPerson104List();

  public void initPerson104List();

  public List<ResearchProject> getProject114List();

  public void initProject114List();
}