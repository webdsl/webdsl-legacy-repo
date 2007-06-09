package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public TechnicalReport getTechnicalReport();

  public void setPublication0(Publication publication5);

  public String cancel();

  public String save();

  public void setNewPublication4(String p);

  public String getNewPublication4();

  public void selectPublication4(ValueChangeEvent event);

  public Map<String, String> getPublication4List();

  public void initPublication4List();

  public List<Person> getPerson1036List();

  public void initPerson1036List();

  public List<ResearchProject> getProject1136List();

  public void initProject1136List();
}