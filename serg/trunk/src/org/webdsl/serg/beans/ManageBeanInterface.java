package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ManageBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public List<Person> getPerson0List();

  public void initPerson0List();

  public List<ResearchProject> getProject0List();

  public void initProject0List();
}