package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateCategoryBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public String cancel();

  public String save();

  public List<Person> getPerson52List();

  public void initPerson52List();

  public List<ResearchProject> getProject39List();

  public void initProject39List();

  public Category getCategory();

  public void setCategory(Category category);
}