package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllProductBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeProduct(Product product5);

  public List<Person> getPerson135List();

  public void initPerson135List();

  public List<ResearchProject> getProject129List();

  public void initProject129List();

  public List<Product> getProduct4List();

  public void initProduct4List();
}