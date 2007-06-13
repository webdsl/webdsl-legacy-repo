package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setUser(User user);

  public User getUser();

  public void setPerson0(Person person111);

  public String cancel();

  public String save();

  public void setNewPerson110(String p);

  public String getNewPerson110();

  public void selectPerson110(ValueChangeEvent event);

  public Map<String, String> getPerson110List();

  public void initPerson110List();

  public List<Person> getPerson21List();

  public void initPerson21List();

  public List<ResearchProject> getProject20List();

  public void initProject20List();
}