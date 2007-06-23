package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setColloquium(Colloquium colloquium);

  public Colloquium getColloquium();

  public List<Person> getPerson16List();

  public void initPerson16List();

  public List<ResearchProject> getProject7List();

  public void initProject7List();
}