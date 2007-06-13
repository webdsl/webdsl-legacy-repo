package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setGroup(ResearchGroup group);

  public ResearchGroup getGroup();

  public List<Person> getPerson15List();

  public void initPerson15List();

  public List<ResearchProject> getProject11List();

  public void initProject11List();

  public java.util.List<Publication> getPublications1();

  public void setPublications1(java.util.List<Publication> publications1);

  public void initPublications1();
}