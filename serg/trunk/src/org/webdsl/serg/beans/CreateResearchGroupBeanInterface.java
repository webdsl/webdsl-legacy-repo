package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson15(Person person206);

  public void addPerson15(Person person206);

  public void removeResearchProject15(ResearchProject researchProject54);

  public void addResearchProject15(ResearchProject researchProject54);

  public void removeColloquium1(Colloquium colloquium9);

  public void addColloquium1(Colloquium colloquium9);

  public void removeNews1(News news8);

  public void addNews1(News news8);

  public String cancel();

  public String save();

  public void setNewPerson207(String p);

  public String getNewPerson207();

  public void selectPerson207(ValueChangeEvent event);

  public Map<String, String> getPerson207List();

  public void initPerson207List();

  public void setNewResearchProject55(String p);

  public String getNewResearchProject55();

  public void selectResearchProject55(ValueChangeEvent event);

  public Map<String, String> getResearchProject55List();

  public void initResearchProject55List();

  public void setNewColloquium10(String p);

  public String getNewColloquium10();

  public void selectColloquium10(ValueChangeEvent event);

  public Map<String, String> getColloquium10List();

  public void initColloquium10List();

  public void setNewNews9(String p);

  public String getNewNews9();

  public void selectNews9(ValueChangeEvent event);

  public Map<String, String> getNews9List();

  public void initNews9List();

  public List<Person> getPerson97List();

  public void initPerson97List();

  public List<ResearchProject> getProject86List();

  public void initProject86List();

  public ResearchGroup getResearchGroup();

  public void setResearchGroup(ResearchGroup researchGroup);
}