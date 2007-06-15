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

  public void removePerson12(Person person178);

  public void addPerson12(Person person178);

  public void removeResearchProject12(ResearchProject researchProject34);

  public void addResearchProject12(ResearchProject researchProject34);

  public void removeColloquium0(Colloquium colloquium5);

  public void addColloquium0(Colloquium colloquium5);

  public void removeNews0(News news5);

  public void addNews0(News news5);

  public String cancel();

  public String save();

  public void setNewPerson179(String p);

  public String getNewPerson179();

  public void selectPerson179(ValueChangeEvent event);

  public Map<String, String> getPerson179List();

  public void initPerson179List();

  public void setNewResearchProject35(String p);

  public String getNewResearchProject35();

  public void selectResearchProject35(ValueChangeEvent event);

  public Map<String, String> getResearchProject35List();

  public void initResearchProject35List();

  public void setNewColloquium6(String p);

  public String getNewColloquium6();

  public void selectColloquium6(ValueChangeEvent event);

  public Map<String, String> getColloquium6List();

  public void initColloquium6List();

  public void setNewNews6(String p);

  public String getNewNews6();

  public void selectNews6(ValueChangeEvent event);

  public Map<String, String> getNews6List();

  public void initNews6List();

  public List<Person> getPerson86List();

  public void initPerson86List();

  public List<ResearchProject> getProject81List();

  public void initProject81List();
}