package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson13(Person person185);

  public void addPerson13(Person person185);

  public void removeResearchProject13(ResearchProject researchProject47);

  public void addResearchProject13(ResearchProject researchProject47);

  public void removeColloquium1(Colloquium colloquium9);

  public void addColloquium1(Colloquium colloquium9);

  public void removeNews1(News news8);

  public void addNews1(News news8);

  public String cancel();

  public String save();

  public void setNewPerson186(String p);

  public String getNewPerson186();

  public void selectPerson186(ValueChangeEvent event);

  public Map<String, String> getPerson186List();

  public void initPerson186List();

  public void setNewResearchProject48(String p);

  public String getNewResearchProject48();

  public void selectResearchProject48(ValueChangeEvent event);

  public Map<String, String> getResearchProject48List();

  public void initResearchProject48List();

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

  public List<Person> getPerson92List();

  public void initPerson92List();

  public List<ResearchProject> getProject82List();

  public void initProject82List();

  public ResearchGroup getResearchGroup();

  public void setResearchGroup(ResearchGroup researchGroup);
}