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

  public void removePerson12(Person person160);

  public void addPerson12(Person person160);

  public void removeResearchProject12(ResearchProject researchProject34);

  public void addResearchProject12(ResearchProject researchProject34);

  public void removeColloquium0(Colloquium colloquium4);

  public void addColloquium0(Colloquium colloquium4);

  public void removeNews0(News news5);

  public void addNews0(News news5);

  public String cancel();

  public String save();

  public void setNewPerson161(String p);

  public String getNewPerson161();

  public void selectPerson161(ValueChangeEvent event);

  public Map<String, String> getPerson161List();

  public void initPerson161List();

  public void setNewResearchProject35(String p);

  public String getNewResearchProject35();

  public void selectResearchProject35(ValueChangeEvent event);

  public Map<String, String> getResearchProject35List();

  public void initResearchProject35List();

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

  public List<Person> getPerson85List();

  public void initPerson85List();

  public List<ResearchProject> getProject80List();

  public void initProject80List();
}