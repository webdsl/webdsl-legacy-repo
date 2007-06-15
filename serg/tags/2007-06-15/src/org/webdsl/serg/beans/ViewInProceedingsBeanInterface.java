package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewInProceedingsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setInProceedings(InProceedings inProceedings);

  public InProceedings getInProceedings();

  public String createNewPerson(Publication publication02, java.util.List<Person> authors2);

  public String createNewResearchProject(Publication publication17, java.util.Set<ResearchProject> projects7);

  public List<Person> getPerson73List();

  public void initPerson73List();

  public List<ResearchProject> getProject63List();

  public void initProject63List();
}