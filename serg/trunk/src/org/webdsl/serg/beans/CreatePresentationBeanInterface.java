package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreatePresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson13(Person person33);

  public String cancel();

  public String save();

  public void setNewPerson32(String p);

  public String getNewPerson32();

  public void selectPerson32(ValueChangeEvent event);

  public Map<String, String> getPerson32List();

  public void initPerson32List();

  public List<Person> getPerson1031List();

  public void initPerson1031List();

  public List<ResearchProject> getProject1131List();

  public void initProject1131List();

  public Presentation getPresentation();

  public void setPresentation(Presentation presentation);
}