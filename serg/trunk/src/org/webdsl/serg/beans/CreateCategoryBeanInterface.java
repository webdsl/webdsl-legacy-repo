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

  public List<Person> getPerson43List();

  public void initPerson43List();

  public List<ResearchProject> getProject38List();

  public void initProject38List();

  public Category getCategory();

  public void setCategory(Category category);
}