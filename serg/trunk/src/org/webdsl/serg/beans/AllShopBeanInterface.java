package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllShopBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeShop(Shop shop14);

  public List<Person> getPerson137List();

  public void initPerson137List();

  public List<ResearchProject> getProject130List();

  public void initProject130List();

  public List<Shop> getShop13List();

  public void initShop13List();
}