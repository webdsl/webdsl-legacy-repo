package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeProject(Project project104);

  public List<Person> getPerson111List();

  public void initPerson111List();

  public List<ResearchProject> getProject102List();

  public void initProject102List();

  public List<Project> getProject103List();

  public void initProject103List();
}