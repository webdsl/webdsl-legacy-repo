package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface HomeBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void initDB();

  public List<Person> getPerson10List();

  public void initPerson10List();

  public List<Publication> getPubList();

  public void initPubList();

  public List<Person> getPersList();

  public void initPersList();
}