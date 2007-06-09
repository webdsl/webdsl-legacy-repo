package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson7(Person person53);

  public void addPerson7(Person person53);

  public void removeResearchProject10(ResearchProject researchProject28);

  public void addResearchProject10(ResearchProject researchProject28);

  public void removeColloquium1(Colloquium colloquium4);

  public void addColloquium1(Colloquium colloquium4);

  public void removeNews1(News news5);

  public void addNews1(News news5);

  public String cancel();

  public String save();

  public void setNewPerson54(String p);

  public String getNewPerson54();

  public void selectPerson54(ValueChangeEvent event);

  public Map<String, String> getPerson54List();

  public void initPerson54List();

  public void setNewResearchProject29(String p);

  public String getNewResearchProject29();

  public void selectResearchProject29(ValueChangeEvent event);

  public Map<String, String> getResearchProject29List();

  public void initResearchProject29List();

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