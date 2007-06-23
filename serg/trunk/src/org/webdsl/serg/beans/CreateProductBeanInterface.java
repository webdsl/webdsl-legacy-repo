package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateProductBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setShop1(Shop shop18);

  public void setProduct6(Product product25);

  public void setProduct7(Product product27);

  public String cancel();

  public String save();

  public void setNewShop17(String p);

  public String getNewShop17();

  public void selectShop17(ValueChangeEvent event);

  public Map<String, String> getShop17List();

  public void initShop17List();

  public void setNewProduct24(String p);

  public String getNewProduct24();

  public void selectProduct24(ValueChangeEvent event);

  public Map<String, String> getProduct24List();

  public void initProduct24List();

  public void setNewProduct26(String p);

  public String getNewProduct26();

  public void selectProduct26(ValueChangeEvent event);

  public Map<String, String> getProduct26List();

  public void initProduct26List();

  public List<Person> getPerson139List();

  public void initPerson139List();

  public List<ResearchProject> getProject132List();

  public void initProject132List();

  public Product getProduct();

  public void setProduct(Product product);
}