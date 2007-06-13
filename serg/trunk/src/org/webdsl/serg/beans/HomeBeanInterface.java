package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface HomeBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public List<ResearchGroup> getGroup9List();

  public void initGroup9List();

  public List<Person> getPerson20List();

  public void initPerson20List();

  public List<ResearchProject> getProject19List();

  public void initProject19List();
}