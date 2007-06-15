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

  public String createNewResearchProject();

  public List<Person> getPerson55List();

  public void initPerson55List();

  public List<ResearchProject> getProject50List();

  public void initProject50List();
}