package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllCategoryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeCategory(Category category5);

  public List<Person> getPerson54List();

  public void initPerson54List();

  public List<ResearchProject> getProject41List();

  public void initProject41List();

  public List<Category> getCategory4List();

  public void initCategory4List();
}