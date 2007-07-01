package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllCartBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeCart(Cart cart6);

  public List<Person> getPerson146List();

  public void initPerson146List();

  public List<ResearchProject> getProject137List();

  public void initProject137List();

  public List<Cart> getCart5List();

  public void initCart5List();
}