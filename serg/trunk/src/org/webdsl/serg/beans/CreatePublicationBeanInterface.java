package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson2(Person person143);

  public void addPerson2(Person person143);

  public void addNewAuthor();

  public void removeResearchProject8(ResearchProject researchProject24);

  public void addResearchProject8(ResearchProject researchProject24);

  public String cancel();

  public String save();

  public void setNewPerson144(String p);

  public String getNewPerson144();

  public void selectPerson144(ValueChangeEvent event);

  public Map<String, String> getPerson144List();

  public void initPerson144List();

  public void setNewResearchProject25(String p);

  public String getNewResearchProject25();

  public void selectResearchProject25(ValueChangeEvent event);

  public Map<String, String> getResearchProject25List();

  public void initResearchProject25List();

  public List<Person> getPerson57List();

  public void initPerson57List();

  public List<ResearchProject> getProject52List();

  public void initProject52List();

  public Publication getPublication();

  public void setPublication(Publication publication);

  public Person getNewAuthor1();

  public void setNewAuthor1(Person newAuthor1);
}