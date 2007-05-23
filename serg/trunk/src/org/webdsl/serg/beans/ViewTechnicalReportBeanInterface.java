package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewTechnicalReportBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setTechnicalReport(TechnicalReport technicalReport);

  public TechnicalReport getTechnicalReport();
}