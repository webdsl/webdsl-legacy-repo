package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPublication(Publication publication);

  public Publication getPublication();

  public List<Person> getPerson1036List();

  public void initPerson1036List();

  public List<ResearchProject> getProject1136List();

  public void initProject1136List();
}