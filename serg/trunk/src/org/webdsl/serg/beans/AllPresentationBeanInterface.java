package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllPresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePresentation(Presentation presentation6);

  public List<Person> getPerson65List();

  public void initPerson65List();

  public List<ResearchProject> getProject52List();

  public void initProject52List();

  public List<Presentation> getPresentation5List();

  public void initPresentation5List();
}