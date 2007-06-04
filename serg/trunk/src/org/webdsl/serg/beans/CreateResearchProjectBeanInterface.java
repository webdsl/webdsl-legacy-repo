package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson4(Person person11);

  public void addPerson4(Person person11);

  public void setPublication1(Publication proposal1);

  public void removePublication1(Publication publication2);

  public void addPublication1(Publication publication2);

  public String cancel();

  public String save();

  public void setNewPerson12(String p);

  public String getNewPerson12();

  public String selectPerson12();

  public Map<String, String> getPerson12List();

  public void initPerson12List();

  public void setNewProposal0(String p);

  public String getNewProposal0();

  public String selectProposal0();

  public Map<String, String> getProposal0List();

  public void initProposal0List();

  public void setNewPublication3(String p);

  public String getNewPublication3();

  public String selectPublication3();

  public Map<String, String> getPublication3List();

  public void initPublication3List();

  public List<Person> getPerson10List();

  public void initPerson10List();

  public ResearchProject getResearchProject();

  public void setResearchProject(ResearchProject researchProject);
}