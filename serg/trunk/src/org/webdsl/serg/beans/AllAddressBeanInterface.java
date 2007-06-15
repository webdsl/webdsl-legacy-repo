package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllAddressBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeAddress(Address address4);

  public List<Person> getPerson34List();

  public void initPerson34List();

  public List<ResearchProject> getProject28List();

  public void initProject28List();

  public List<Address> getAddress3List();

  public void initAddress3List();
}