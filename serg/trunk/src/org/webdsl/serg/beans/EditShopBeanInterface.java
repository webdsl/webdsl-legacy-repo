package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditShopBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setShop(Shop shop);

  public Shop getShop();

  public void removeProduct0(Product product7);

  public void addProduct0(Product product7);

  public void removeCart0(Cart cart7);

  public void addCart0(Cart cart7);

  public void setProduct0(Product product11);

  public void setProduct1(Product product13);

  public String cancel();

  public String save();

  public void setNewProduct8(String p);

  public String getNewProduct8();

  public void selectProduct8(ValueChangeEvent event);

  public Map<String, String> getProduct8List();

  public void initProduct8List();

  public void setNewCart8(String p);

  public String getNewCart8();

  public void selectCart8(ValueChangeEvent event);

  public Map<String, String> getCart8List();

  public void initCart8List();

  public void setNewProduct9(String p);

  public String getNewProduct9();

  public void selectProduct9(ValueChangeEvent event);

  public Map<String, String> getProduct9List();

  public void initProduct9List();

  public void setNewProduct12(String p);

  public String getNewProduct12();

  public void selectProduct12(ValueChangeEvent event);

  public Map<String, String> getProduct12List();

  public void initProduct12List();

  public List<Person> getPerson135List();

  public void initPerson135List();

  public List<ResearchProject> getProject128List();

  public void initProject128List();
}