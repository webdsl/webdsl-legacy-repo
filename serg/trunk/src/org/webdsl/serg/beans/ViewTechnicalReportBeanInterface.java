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

  public List<Person> getPerson1039List();

  public void initPerson1039List();

  public List<ResearchProject> getProject1139List();

  public void initProject1139List();
}