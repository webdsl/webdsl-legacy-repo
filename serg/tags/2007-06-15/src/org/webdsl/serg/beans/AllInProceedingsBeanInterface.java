package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllInProceedingsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeInProceedings(InProceedings inProceedings4);

  public List<Person> getPerson74List();

  public void initPerson74List();

  public List<ResearchProject> getProject64List();

  public void initProject64List();

  public List<InProceedings> getInProceedings3List();

  public void initInProceedings3List();
}