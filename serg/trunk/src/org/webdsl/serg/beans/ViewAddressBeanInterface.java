package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewAddressBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setAddress(Address address);

  public Address getAddress();

  public List<Person> getPerson1012List();

  public void initPerson1012List();

  public List<ResearchProject> getProject1112List();

  public void initProject1112List();
}