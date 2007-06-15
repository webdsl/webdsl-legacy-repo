package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeProject(Project project100);

  public List<Person> getPerson100List();

  public void initPerson100List();

  public List<ResearchProject> getProject98List();

  public void initProject98List();

  public List<Project> getProject99List();

  public void initProject99List();
}