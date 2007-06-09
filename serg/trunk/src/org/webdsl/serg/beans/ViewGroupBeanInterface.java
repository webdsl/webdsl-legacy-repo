package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setGroup(ResearchGroup group);

  public ResearchGroup getGroup();

  public List<Person> getPerson106List();

  public void initPerson106List();

  public List<ResearchProject> getProject116List();

  public void initProject116List();

  public List<Publication> getPub1List();

  public void initPub1List();
}