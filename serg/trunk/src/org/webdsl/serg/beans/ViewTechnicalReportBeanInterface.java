package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public TechnicalReport getTechnicalReport();

  public String createNewPerson(Publication publication01, java.util.List<Person> authors2);

  public String createNewResearchProject(Publication publication13, java.util.Set<ResearchProject> projects7);

  public List<Person> getPerson76List();

  public void initPerson76List();

  public List<ResearchProject> getProject63List();

  public void initProject63List();
}