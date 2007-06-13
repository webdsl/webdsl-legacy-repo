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

  public List<Person> getPerson25List();

  public void initPerson25List();

  public List<ResearchProject> getProject24List();

  public void initProject24List();
}