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

  public List<Person> getPerson1016List();

  public void initPerson1016List();

  public List<ResearchProject> getProject1116List();

  public void initProject1116List();
}