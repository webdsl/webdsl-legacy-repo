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

  public List<Person> getPerson42List();

  public void initPerson42List();

  public List<ResearchProject> getProject37List();

  public void initProject37List();

  public Category getCategory();

  public void setCategory(Category category);
}