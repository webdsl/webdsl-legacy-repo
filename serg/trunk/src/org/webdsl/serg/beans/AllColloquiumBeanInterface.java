package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeColloquium(Colloquium colloquium6);

  public List<Person> getPerson61List();

  public void initPerson61List();

  public List<ResearchProject> getProject48List();

  public void initProject48List();

  public List<Colloquium> getColloquium5List();

  public void initColloquium5List();
}