package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllUserBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeUser(User user4);

  public List<Person> getPerson30List();

  public void initPerson30List();

  public List<ResearchProject> getProject24List();

  public void initProject24List();

  public List<User> getUser3List();

  public void initUser3List();
}