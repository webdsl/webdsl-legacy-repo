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

  public List<Person> getPerson42List();

  public void initPerson42List();

  public List<ResearchProject> getProject37List();

  public void initProject37List();
}