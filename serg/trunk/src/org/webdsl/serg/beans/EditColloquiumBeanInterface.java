package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setColloquium(Colloquium colloquium);

  public Colloquium getColloquium();

  public void removePresentation0(Presentation presentation3);

  public void addPresentation0(Presentation presentation3);

  public void setPerson9(Person person25);

  public String cancel();

  public String save();

  public void setNewPresentation4(String p);

  public String getNewPresentation4();

  public void selectPresentation4(ValueChangeEvent event);

  public Map<String, String> getPresentation4List();

  public void initPresentation4List();

  public void setNewPerson24(String p);

  public String getNewPerson24();

  public void selectPerson24(ValueChangeEvent event);

  public Map<String, String> getPerson24List();

  public void initPerson24List();

  public List<Person> getPerson1027List();

  public void initPerson1027List();

  public List<ResearchProject> getProject1127List();

  public void initProject1127List();
}