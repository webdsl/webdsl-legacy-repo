package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditAddressBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setAddress(Address address);

  public Address getAddress();

  public String cancel();

  public String save();

  public List<Person> getPerson1010List();

  public void initPerson1010List();

  public List<ResearchProject> getProject1110List();

  public void initProject1110List();
}