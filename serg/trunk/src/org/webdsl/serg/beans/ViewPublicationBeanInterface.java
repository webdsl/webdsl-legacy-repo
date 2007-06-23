package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPublication(Publication publication);

  public Publication getPublication();

  public String createNewPerson(Publication publication00, java.util.List<Person> authors0);

  public String createNewResearchProject(Publication publication10, java.util.Set<ResearchProject> projects5);

  public List<Person> getPerson66List();

  public void initPerson66List();

  public List<ResearchProject> getProject55List();

  public void initProject55List();
}