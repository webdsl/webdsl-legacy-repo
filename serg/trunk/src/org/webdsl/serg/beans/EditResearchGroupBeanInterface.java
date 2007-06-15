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

  public void removePerson12(Person person182);

  public void addPerson12(Person person182);

  public void removeResearchProject12(ResearchProject researchProject39);

  public void addResearchProject12(ResearchProject researchProject39);

  public void removeColloquium0(Colloquium colloquium5);

  public void addColloquium0(Colloquium colloquium5);

  public void removeNews0(News news5);

  public void addNews0(News news5);

  public String cancel();

  public String save();

  public void setNewPerson183(String p);

  public String getNewPerson183();

  public void selectPerson183(ValueChangeEvent event);

  public Map<String, String> getPerson183List();

  public void initPerson183List();

  public void setNewResearchProject40(String p);

  public String getNewResearchProject40();

  public void selectResearchProject40(ValueChangeEvent event);

  public Map<String, String> getResearchProject40List();

  public void initResearchProject40List();

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

  public List<Person> getPerson91List();

  public void initPerson91List();

  public List<ResearchProject> getProject80List();

  public void initProject80List();
}