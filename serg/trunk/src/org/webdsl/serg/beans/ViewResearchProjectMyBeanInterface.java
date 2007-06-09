package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewResearchProjectMyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProject(ResearchProject project);

  public ResearchProject getProject();

  public List<Person> getPerson105List();

  public void initPerson105List();

  public List<ResearchProject> getProject115List();

  public void initProject115List();
}