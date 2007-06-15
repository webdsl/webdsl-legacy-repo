package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllResearchProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeResearchProject(ResearchProject researchProject9);

  public List<Person> getPerson90List();

  public void initPerson90List();

  public List<ResearchProject> getProject80List();

  public void initProject80List();

  public List<ResearchProject> getResearchProject8List();

  public void initResearchProject8List();
}