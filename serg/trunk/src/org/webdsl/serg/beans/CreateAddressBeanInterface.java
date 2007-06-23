package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateAddressBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public String cancel();

  public String save();

  public List<Person> getPerson33List();

  public void initPerson33List();

  public List<ResearchProject> getProject26List();

  public void initProject26List();

  public Address getAddress();

  public void setAddress(Address address);
}