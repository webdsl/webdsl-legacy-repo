package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewCartBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setCart(Cart cart);

  public Cart getCart();

  public String createNewProduct(Cart cart10, java.util.List<Product> products0);

  public List<Person> getPerson145List();

  public void initPerson145List();

  public List<ResearchProject> getProject136List();

  public void initProject136List();
}