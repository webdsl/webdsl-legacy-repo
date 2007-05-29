package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setResearchProject(ResearchProject researchProject);

  public ResearchProject getResearchProject();

  public void removePerson2(Person person6);

  public void addPerson2(Person person6);

  public void setPublication1(Publication proposal1);

  public void removePublication0(Publication publication0);

  public void addPublication0(Publication publication0);

  public String cancel();

  public String save();

  public void setNewPerson7(String p);

  public String getNewPerson7();

  public String selectPerson7();

  public Map<String, String> getPerson7List();

  public void initPerson7List();

  public void setNewProposal0(String p);

  public String getNewProposal0();

  public String selectProposal0();

  public Map<String, String> getProposal0List();

  public void initProposal0List();

  public void setNewPublication1(String p);

  public String getNewPublication1();

  public String selectPublication1();

  public Map<String, String> getPublication1List();

  public void initPublication1List();
}