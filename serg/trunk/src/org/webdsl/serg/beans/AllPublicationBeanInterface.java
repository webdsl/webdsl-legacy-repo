package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePublication(Publication publication8);

  public List<Person> getPerson69List();

  public void initPerson69List();

  public List<ResearchProject> getProject56List();

  public void initProject56List();

  public List<Publication> getPublication7List();

  public void initPublication7List();
}