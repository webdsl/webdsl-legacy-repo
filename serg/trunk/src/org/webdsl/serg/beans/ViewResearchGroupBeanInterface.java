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

  public List<Person> getPerson107List();

  public void initPerson107List();

  public List<ResearchProject> getProject117List();

  public void initProject117List();

  public List<Publication> getPub1List();

  public void initPub1List();
}