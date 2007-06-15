package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePublication(Publication publication5);

  public List<Person> getPerson61List();

  public void initPerson61List();

  public List<ResearchProject> getProject56List();

  public void initProject56List();

  public List<Publication> getPublication4List();

  public void initPublication4List();
}