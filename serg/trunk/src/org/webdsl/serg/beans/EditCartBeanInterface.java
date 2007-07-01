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

  public void setPerson17(Person person256);

  public void removeProduct2(Product product28);

  public void addProduct2(Product product28);

  public String cancel();

  public String save();

  public void setNewPerson255(String p);

  public String getNewPerson255();

  public void selectPerson255(ValueChangeEvent event);

  public Map<String, String> getPerson255List();

  public void initPerson255List();

  public void setNewProduct29(String p);

  public String getNewProduct29();

  public void selectProduct29(ValueChangeEvent event);

  public Map<String, String> getProduct29List();

  public void initProduct29List();

  public List<Person> getPerson143List();

  public void initPerson143List();

  public List<ResearchProject> getProject134List();

  public void initProject134List();
}