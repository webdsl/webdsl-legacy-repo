package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateShopBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeProduct1(Product product14);

  public void addProduct1(Product product14);

  public void removeCart1(Cart cart9);

  public void addCart1(Cart cart9);

  public void setProduct2(Product product17);

  public void setProduct3(Product product19);

  public String cancel();

  public String save();

  public void setNewProduct15(String p);

  public String getNewProduct15();

  public void selectProduct15(ValueChangeEvent event);

  public Map<String, String> getProduct15List();

  public void initProduct15List();

  public void setNewCart11(String p);

  public String getNewCart11();

  public void selectCart11(ValueChangeEvent event);

  public Map<String, String> getCart11List();

  public void initCart11List();

  public void setNewProduct16(String p);

  public String getNewProduct16();

  public void selectProduct16(ValueChangeEvent event);

  public Map<String, String> getProduct16List();

  public void initProduct16List();

  public void setNewProduct18(String p);

  public String getNewProduct18();

  public void selectProduct18(ValueChangeEvent event);

  public Map<String, String> getProduct18List();

  public void initProduct18List();

  public List<Person> getPerson138List();

  public void initPerson138List();

  public List<ResearchProject> getProject129List();

  public void initProject129List();

  public Shop getShop();

  public void setShop(Shop shop);
}