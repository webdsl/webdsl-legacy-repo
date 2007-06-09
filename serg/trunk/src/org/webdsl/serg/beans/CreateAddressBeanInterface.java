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

  public List<Person> getPerson1011List();

  public void initPerson1011List();

  public List<ResearchProject> getProject1111List();

  public void initProject1111List();

  public Address getAddress();

  public void setAddress(Address address);
}