package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPublication(Publication publication);

  public Publication getPublication();

  public void removePerson0(Person person2);

  public void addPerson0(Person person2);

  public void removeResearchProject0(ResearchProject researchProject0);

  public void addResearchProject0(ResearchProject researchProject0);

  public String cancel();

  public String save();

  public void setNewPerson3(String p);

  public String getNewPerson3();

  public String selectPerson3();

  public Map<String, String> getPerson3List();

  public void initPerson3List();

  public void setNewResearchProject1(String p);

  public String getNewResearchProject1();

  public String selectResearchProject1();

  public Map<String, String> getResearchProject1List();

  public void initResearchProject1List();

  public List<Person> getPerson10List();

  public void initPerson10List();
}