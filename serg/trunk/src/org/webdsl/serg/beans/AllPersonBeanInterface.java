package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllPersonBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson(Person person36);

  public List<Person> getPerson34List();

  public void initPerson34List();

  public List<ResearchProject> getProject31List();

  public void initProject31List();

  public List<Person> getPerson35List();

  public void initPerson35List();
}