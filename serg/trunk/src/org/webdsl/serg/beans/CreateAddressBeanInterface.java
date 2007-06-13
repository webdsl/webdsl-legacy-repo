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

  public List<Person> getPerson26List();

  public void initPerson26List();

  public List<ResearchProject> getProject25List();

  public void initProject25List();

  public Address getAddress();

  public void setAddress(Address address);
}