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

  public String createNewPerson();

  public String createNewResearchProject();

  public List<Person> getPerson59List();

  public void initPerson59List();

  public List<ResearchProject> getProject54List();

  public void initProject54List();
}