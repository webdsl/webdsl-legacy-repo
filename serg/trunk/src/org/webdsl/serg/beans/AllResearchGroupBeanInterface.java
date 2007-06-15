package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeResearchGroup(ResearchGroup researchGroup7);

  public List<Person> getPerson93List();

  public void initPerson93List();

  public List<ResearchProject> getProject83List();

  public void initProject83List();

  public List<ResearchGroup> getResearchGroup6List();

  public void initResearchGroup6List();
}