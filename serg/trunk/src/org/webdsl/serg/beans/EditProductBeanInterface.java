package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditProductBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProduct(Product product);

  public Product getProduct();

  public void setShop0(Shop shop16);

  public void setProduct4(Product product21);

  public void setProduct5(Product product23);

  public String cancel();

  public String save();

  public void setNewShop15(String p);

  public String getNewShop15();

  public void selectShop15(ValueChangeEvent event);

  public Map<String, String> getShop15List();

  public void initShop15List();

  public void setNewProduct20(String p);

  public String getNewProduct20();

  public void selectProduct20(ValueChangeEvent event);

  public Map<String, String> getProduct20List();

  public void initProduct20List();

  public void setNewProduct22(String p);

  public String getNewProduct22();

  public void selectProduct22(ValueChangeEvent event);

  public Map<String, String> getProduct22List();

  public void initProduct22List();

  public List<Person> getPerson138List();

  public void initPerson138List();

  public List<ResearchProject> getProject131List();

  public void initProject131List();
}