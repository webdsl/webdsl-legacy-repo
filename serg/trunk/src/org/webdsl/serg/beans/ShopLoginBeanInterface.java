package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ShopLoginBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setShop(Shop shop);

  public Shop getShop();

  public String addProduct(Shop shop10);

  public String login();

  public User getUser();

  public void setUser(User user);
}