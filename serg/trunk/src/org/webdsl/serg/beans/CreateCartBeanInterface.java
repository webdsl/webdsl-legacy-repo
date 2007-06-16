package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateCartBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPerson18(Person person234);

  public void removeProduct3(Product product30);

  public void addProduct3(Product product30);

  public String cancel();

  public String save();

  public void setNewPerson233(String p);

  public String getNewPerson233();

  public void selectPerson233(ValueChangeEvent event);

  public Map<String, String> getPerson233List();

  public void initPerson233List();

  public void setNewProduct31(String p);

  public String getNewProduct31();

  public void selectProduct31(ValueChangeEvent event);

  public Map<String, String> getProduct31List();

  public void initProduct31List();

  public List<Person> getPerson137List();

  public void initPerson137List();

  public List<ResearchProject> getProject131List();

  public void initProject131List();

  public Cart getCart();

  public void setCart(Cart cart);
}