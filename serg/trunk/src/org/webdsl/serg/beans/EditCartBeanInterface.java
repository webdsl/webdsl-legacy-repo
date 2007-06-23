package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditCartBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setCart(Cart cart);

  public Cart getCart();

  public void setPerson17(Person person242);

  public void removeProduct2(Product product28);

  public void addProduct2(Product product28);

  public String cancel();

  public String save();

  public void setNewPerson241(String p);

  public String getNewPerson241();

  public void selectPerson241(ValueChangeEvent event);

  public Map<String, String> getPerson241List();

  public void initPerson241List();

  public void setNewProduct29(String p);

  public String getNewProduct29();

  public void selectProduct29(ValueChangeEvent event);

  public Map<String, String> getProduct29List();

  public void initProduct29List();

  public List<Person> getPerson141List();

  public void initPerson141List();

  public List<ResearchProject> getProject134List();

  public void initProject134List();
}