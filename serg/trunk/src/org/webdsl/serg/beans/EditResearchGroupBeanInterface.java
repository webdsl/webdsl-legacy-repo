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

  public void removePerson15(Person person178);

  public void addPerson15(Person person178);

  public void removeResearchProject15(ResearchProject researchProject43);

  public void addResearchProject15(ResearchProject researchProject43);

  public void removeColloquium0(Colloquium colloquium4);

  public void addColloquium0(Colloquium colloquium4);

  public void removeNews0(News news5);

  public void addNews0(News news5);

  public String cancel();

  public String save();

  public void setNewPerson179(String p);

  public String getNewPerson179();

  public void selectPerson179(ValueChangeEvent event);

  public Map<String, String> getPerson179List();

  public void initPerson179List();

  public void setNewResearchProject44(String p);

  public String getNewResearchProject44();

  public void selectResearchProject44(ValueChangeEvent event);

  public Map<String, String> getResearchProject44List();

  public void initResearchProject44List();

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

  public List<Person> getPerson84List();

  public void initPerson84List();

  public List<ResearchProject> getProject79List();

  public void initProject79List();
}