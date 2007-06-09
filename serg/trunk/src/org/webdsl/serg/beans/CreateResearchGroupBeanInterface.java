package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson7(Person person52);

  public void addPerson7(Person person52);

  public void removeResearchProject4(ResearchProject researchProject13);

  public void addResearchProject4(ResearchProject researchProject13);

  public void removeNews1(News news5);

  public void addNews1(News news5);

  public String cancel();

  public String save();

  public void setNewPerson53(String p);

  public String getNewPerson53();

  public void selectPerson53(ValueChangeEvent event);

  public Map<String, String> getPerson53List();

  public void initPerson53List();

  public void setNewResearchProject14(String p);

  public String getNewResearchProject14();

  public void selectResearchProject14(ValueChangeEvent event);

  public Map<String, String> getResearchProject14List();

  public void initResearchProject14List();

  public void setNewNews6(String p);

  public String getNewNews6();

  public void selectNews6(ValueChangeEvent event);

  public Map<String, String> getNews6List();

  public void initNews6List();

  public List<Person> getPerson1043List();

  public void initPerson1043List();

  public List<ResearchProject> getProject1143List();

  public void initProject1143List();

  public ResearchGroup getResearchGroup();

  public void setResearchGroup(ResearchGroup researchGroup);
}