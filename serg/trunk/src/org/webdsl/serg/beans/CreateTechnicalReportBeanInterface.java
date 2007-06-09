package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPublication1(Publication publication7);

  public String cancel();

  public String save();

  public void setNewPublication6(String p);

  public String getNewPublication6();

  public void selectPublication6(ValueChangeEvent event);

  public Map<String, String> getPublication6List();

  public void initPublication6List();

  public List<Person> getPerson1038List();

  public void initPerson1038List();

  public List<ResearchProject> getProject1138List();

  public void initProject1138List();

  public TechnicalReport getTechnicalReport();

  public void setTechnicalReport(TechnicalReport technicalReport);
}