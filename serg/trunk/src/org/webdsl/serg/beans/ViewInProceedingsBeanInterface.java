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

  public List<Person> getPerson68List();

  public void initPerson68List();

  public List<ResearchProject> getProject63List();

  public void initProject63List();
}