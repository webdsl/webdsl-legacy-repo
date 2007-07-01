package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateCartBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson18(Person person258);

  public void removeProduct3(Product product30);

  public void addProduct3(Product product30);

  public String cancel();

  public String save();

  public void setNewPerson257(String p);

  public String getNewPerson257();

  public void selectPerson257(ValueChangeEvent event);

  public Map<String, String> getPerson257List();

  public void initPerson257List();

  public void setNewProduct31(String p);

  public String getNewProduct31();

  public void selectProduct31(ValueChangeEvent event);

  public Map<String, String> getProduct31List();

  public void initProduct31List();

  public List<Person> getPerson144List();

  public void initPerson144List();

  public List<ResearchProject> getProject135List();

  public void initProject135List();

  public Cart getCart();

  public void setCart(Cart cart);
}