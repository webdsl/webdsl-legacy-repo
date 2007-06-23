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

  public String createNewPerson(Publication publication02, java.util.List<Person> authors3);

  public String createNewResearchProject(Publication publication17, java.util.Set<ResearchProject> projects8);

  public List<Person> getPerson78List();

  public void initPerson78List();

  public List<ResearchProject> getProject67List();

  public void initProject67List();
}