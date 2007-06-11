package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson9(Person person64);

  public void addPerson9(Person person64);

  public void removeResearchProject12(ResearchProject researchProject33);

  public void addResearchProject12(ResearchProject researchProject33);

  public void removeColloquium1(Colloquium colloquium4);

  public void addColloquium1(Colloquium colloquium4);

  public void removeNews1(News news5);

  public void addNews1(News news5);

  public String cancel();

  public String save();

  public void setNewPerson65(String p);

  public String getNewPerson65();

  public void selectPerson65(ValueChangeEvent event);

  public Map<String, String> getPerson65List();

  public void initPerson65List();

  public void setNewResearchProject34(String p);

  public String getNewResearchProject34();

  public void selectResearchProject34(ValueChangeEvent event);

  public Map<String, String> getResearchProject34List();

  public void initResearchProject34List();

  public void setNewColloquium5(String p);

  public String getNewColloquium5();

  public void selectColloquium5(ValueChangeEvent event);

  public Map<String, String> getColloquium5List();

  public void initColloquium5List();

  public void setNewNews6(String p);

  public String getNewNews6();

  public void selectNews6(ValueChangeEvent event);

  public Map<String, String> getNews6List();

  public void initNews6List();

  public List<Person> getPerson1044List();

  public void initPerson1044List();

  public List<ResearchProject> getProject1144List();

  public void initProject1144List();

  public ResearchGroup getResearchGroup();

  public void setResearchGroup(ResearchGroup researchGroup);
}