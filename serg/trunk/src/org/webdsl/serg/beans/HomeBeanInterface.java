package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface HomeBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public List<Person> getPerson26List();

  public void initPerson26List();

  public List<ResearchProject> getProject20List();

  public void initProject20List();

  public List<ResearchGroup> getX0List();

  public void initX0List();

  public List<ResearchProject> getX1List();

  public void initX1List();

  public List<Forum> getX2List();

  public void initX2List();

  public List<Blog> getX3List();

  public void initX3List();

  public List<Project> getX4List();

  public void initX4List();

  public List<Person> getX5List();

  public void initX5List();
}