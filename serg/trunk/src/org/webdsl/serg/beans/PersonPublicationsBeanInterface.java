package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface PersonPublicationsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setP(Person p);

  public Person getP();

  public List<ResearchProject> getPr3List();

  public void initPr3List();

  public List<Person> getPerson105List();

  public void initPerson105List();

  public List<ResearchProject> getProject115List();

  public void initProject115List();
}