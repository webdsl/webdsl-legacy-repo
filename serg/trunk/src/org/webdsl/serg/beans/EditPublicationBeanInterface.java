package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPublication(Publication publication);

  public Publication getPublication();

  public void removePerson0(Person person183);

  public void addPerson0(Person person183);

  public void addNewAuthor();

  public void removeResearchProject4(ResearchProject researchProject25);

  public void addResearchProject4(ResearchProject researchProject25);

  public String cancel();

  public String save();

  public void setNewPerson184(String p);

  public String getNewPerson184();

  public void selectPerson184(ValueChangeEvent event);

  public Map<String, String> getPerson184List();

  public void initPerson184List();

  public void setNewResearchProject26(String p);

  public String getNewResearchProject26();

  public void selectResearchProject26(ValueChangeEvent event);

  public Map<String, String> getResearchProject26List();

  public void initResearchProject26List();

  public List<Person> getPerson66List();

  public void initPerson66List();

  public List<ResearchProject> getProject53List();

  public void initProject53List();

  public Person getNewAuthor0();

  public void setNewAuthor0(Person newAuthor0);
}