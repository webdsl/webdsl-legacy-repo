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

  public List<Person> getPerson1024List();

  public void initPerson1024List();

  public List<ResearchProject> getProject1124List();

  public void initProject1124List();

  public Category getCategory();

  public void setCategory(Category category);
}