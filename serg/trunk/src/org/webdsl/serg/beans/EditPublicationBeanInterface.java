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

  public void removePerson0(Person person148);

  public void addPerson0(Person person148);

  public void addNewAuthor();

  public void removeResearchProject4(ResearchProject researchProject14);

  public void addResearchProject4(ResearchProject researchProject14);

  public String cancel();

  public String save();

  public void setNewPerson149(String p);

  public String getNewPerson149();

  public void selectPerson149(ValueChangeEvent event);

  public Map<String, String> getPerson149List();

  public void initPerson149List();

  public void setNewResearchProject15(String p);

  public String getNewResearchProject15();

  public void selectResearchProject15(ValueChangeEvent event);

  public Map<String, String> getResearchProject15List();

  public void initResearchProject15List();

  public List<Person> getPerson58List();

  public void initPerson58List();

  public List<ResearchProject> getProject53List();

  public void initProject53List();

  public Person getNewAuthor0();

  public void setNewAuthor0(Person newAuthor0);
}