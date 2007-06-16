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

  public void setPerson17(Person person232);

  public void removeProduct2(Product product28);

  public void addProduct2(Product product28);

  public String cancel();

  public String save();

  public void setNewPerson231(String p);

  public String getNewPerson231();

  public void selectPerson231(ValueChangeEvent event);

  public Map<String, String> getPerson231List();

  public void initPerson231List();

  public void setNewProduct29(String p);

  public String getNewProduct29();

  public void selectProduct29(ValueChangeEvent event);

  public Map<String, String> getProduct29List();

  public void initProduct29List();

  public List<Person> getPerson136List();

  public void initPerson136List();

  public List<ResearchProject> getProject130List();

  public void initProject130List();
}