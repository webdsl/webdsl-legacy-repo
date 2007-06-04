package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public TechnicalReport getTechnicalReport();

  public void setPublication0(Publication preprintof1);

  public String cancel();

  public String save();

  public void setNewPreprintof0(String p);

  public String getNewPreprintof0();

  public String selectPreprintof0();

  public Map<String, String> getPreprintof0List();

  public void initPreprintof0List();

  public List<Person> getPerson10List();

  public void initPerson10List();
}