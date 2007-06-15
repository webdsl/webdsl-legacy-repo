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

  public String createNewPerson();

  public String createNewResearchProject();

  public List<Person> getPerson64List();

  public void initPerson64List();

  public List<ResearchProject> getProject58List();

  public void initProject58List();
}