package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson1(Person person4);

  public void addPerson1(Person person4);

  public void removeResearchProject1(ResearchProject researchProject2);

  public void addResearchProject1(ResearchProject researchProject2);

  public String cancel();

  public String save();

  public void setNewPerson5(String p);

  public String getNewPerson5();

  public String selectPerson5();

  public Map<String, String> getPerson5List();

  public void initPerson5List();

  public void setNewResearchProject3(String p);

  public String getNewResearchProject3();

  public String selectResearchProject3();

  public Map<String, String> getResearchProject3List();

  public void initResearchProject3List();

  public List<Person> getPerson10List();

  public void initPerson10List();

  public Publication getPublication();

  public void setPublication(Publication publication);
}