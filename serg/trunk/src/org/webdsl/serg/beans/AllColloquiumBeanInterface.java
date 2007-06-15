package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeColloquium(Colloquium colloquium4);

  public List<Person> getPerson53List();

  public void initPerson53List();

  public List<ResearchProject> getProject48List();

  public void initProject48List();

  public List<Colloquium> getColloquium3List();

  public void initColloquium3List();
}