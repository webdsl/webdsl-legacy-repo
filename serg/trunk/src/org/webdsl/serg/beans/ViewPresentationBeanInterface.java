package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPresentation(Presentation presentation);

  public Presentation getPresentation();

  public List<Person> getPerson1033List();

  public void initPerson1033List();

  public List<ResearchProject> getProject1133List();

  public void initProject1133List();
}