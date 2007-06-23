package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateCartBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson18(Person person244);

  public void removeProduct3(Product product30);

  public void addProduct3(Product product30);

  public String cancel();

  public String save();

  public void setNewPerson243(String p);

  public String getNewPerson243();

  public void selectPerson243(ValueChangeEvent event);

  public Map<String, String> getPerson243List();

  public void initPerson243List();

  public void setNewProduct31(String p);

  public String getNewProduct31();

  public void selectProduct31(ValueChangeEvent event);

  public Map<String, String> getProduct31List();

  public void initProduct31List();

  public List<Person> getPerson142List();

  public void initPerson142List();

  public List<ResearchProject> getProject135List();

  public void initProject135List();

  public Cart getCart();

  public void setCart(Cart cart);
}