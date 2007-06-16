package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewProductBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setProduct(Product product);

  public Product getProduct();

  public String addProduct(Shop shop7);
}