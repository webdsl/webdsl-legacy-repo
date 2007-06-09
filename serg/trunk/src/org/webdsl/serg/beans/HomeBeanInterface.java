package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface HomeBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void initDB();

  public List<Person> getPerson101List();

  public void initPerson101List();

  public List<ResearchProject> getProject111List();

  public void initProject111List();

  public List<Publication> getPub0List();

  public void initPub0List();

  public List<Person> getPers0List();

  public void initPers0List();
}