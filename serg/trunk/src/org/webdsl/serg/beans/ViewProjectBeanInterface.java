package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewProjectBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProject(Project project);

  public Project getProject();

  public List<Person> getPerson1053List();

  public void initPerson1053List();

  public List<ResearchProject> getProject1153List();

  public void initProject1153List();
}