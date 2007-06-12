package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setGroup(ResearchGroup group);

  public ResearchGroup getGroup();

  public List<Person> getPerson14List();

  public void initPerson14List();

  public List<ResearchProject> getProject10List();

  public void initProject10List();

  public java.util.List<Publication> getPublications1();

  public void setPublications1(java.util.List<Publication> publications1);

  public void initPublications1();
}