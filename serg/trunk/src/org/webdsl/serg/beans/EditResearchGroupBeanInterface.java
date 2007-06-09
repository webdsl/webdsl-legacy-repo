package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setResearchGroup(ResearchGroup researchGroup);

  public ResearchGroup getResearchGroup();

  public void removePerson6(Person person50);

  public void addPerson6(Person person50);

  public void removeResearchProject3(ResearchProject researchProject11);

  public void addResearchProject3(ResearchProject researchProject11);

  public void removeNews0(News news3);

  public void addNews0(News news3);

  public String cancel();

  public String save();

  public void setNewPerson51(String p);

  public String getNewPerson51();

  public void selectPerson51(ValueChangeEvent event);

  public Map<String, String> getPerson51List();

  public void initPerson51List();

  public void setNewResearchProject12(String p);

  public String getNewResearchProject12();

  public void selectResearchProject12(ValueChangeEvent event);

  public Map<String, String> getResearchProject12List();

  public void initResearchProject12List();

  public void setNewNews4(String p);

  public String getNewNews4();

  public void selectNews4(ValueChangeEvent event);

  public Map<String, String> getNews4List();

  public void initNews4List();

  public List<Person> getPerson1042List();

  public void initPerson1042List();

  public List<ResearchProject> getProject1142List();

  public void initProject1142List();
}