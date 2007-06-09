package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditCategoryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setCategory(Category category);

  public Category getCategory();

  public String cancel();

  public String save();

  public List<Person> getPerson1021List();

  public void initPerson1021List();

  public List<ResearchProject> getProject1121List();

  public void initProject1121List();
}