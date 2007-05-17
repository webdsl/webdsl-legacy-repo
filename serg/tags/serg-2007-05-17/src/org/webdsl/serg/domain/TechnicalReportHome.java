package org.webdsl.serg.domain;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("technicalReportHome") public class TechnicalReportHome extends EntityHome<TechnicalReport> 
{ 
  @Factory("technicalReport") public TechnicalReport initTechnicalReport()
  { 
    return getInstance();
  }
}