package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPresentation(Presentation presentation);

  public Presentation getPresentation();

  public void setPerson12(Person person31);

  public String cancel();

  public String save();

  public void setNewPerson30(String p);

  public String getNewPerson30();

  public void selectPerson30(ValueChangeEvent event);

  public Map<String, String> getPerson30List();

  public void initPerson30List();

  public List<Person> getPerson1030List();

  public void initPerson1030List();

  public List<ResearchProject> getProject1130List();

  public void initProject1130List();
}