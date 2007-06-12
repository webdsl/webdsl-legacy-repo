package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface HomeBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public List<ResearchGroup> getGroup8List();

  public void initGroup8List();

  public List<Person> getPerson19List();

  public void initPerson19List();

  public List<ResearchProject> getProject18List();

  public void initProject18List();
}