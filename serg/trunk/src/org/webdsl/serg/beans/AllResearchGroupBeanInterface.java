package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeResearchGroup(ResearchGroup researchGroup3);

  public List<Person> getPerson88List();

  public void initPerson88List();

  public List<ResearchProject> getProject83List();

  public void initProject83List();

  public List<ResearchGroup> getResearchGroup2List();

  public void initResearchGroup2List();
}