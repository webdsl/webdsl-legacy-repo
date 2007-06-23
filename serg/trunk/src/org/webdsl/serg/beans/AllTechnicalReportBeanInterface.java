package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeTechnicalReport(TechnicalReport technicalReport4);

  public List<Person> getPerson75List();

  public void initPerson75List();

  public List<ResearchProject> getProject64List();

  public void initProject64List();

  public List<TechnicalReport> getTechnicalReport3List();

  public void initTechnicalReport3List();
}