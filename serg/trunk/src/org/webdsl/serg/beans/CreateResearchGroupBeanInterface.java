package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson16(Person person180);

  public void addPerson16(Person person180);

  public void removeResearchProject16(ResearchProject researchProject45);

  public void addResearchProject16(ResearchProject researchProject45);

  public void removeColloquium1(Colloquium colloquium6);

  public void addColloquium1(Colloquium colloquium6);

  public void removeNews1(News news7);

  public void addNews1(News news7);

  public String cancel();

  public String save();

  public void setNewPerson181(String p);

  public String getNewPerson181();

  public void selectPerson181(ValueChangeEvent event);

  public Map<String, String> getPerson181List();

  public void initPerson181List();

  public void setNewResearchProject46(String p);

  public String getNewResearchProject46();

  public void selectResearchProject46(ValueChangeEvent event);

  public Map<String, String> getResearchProject46List();

  public void initResearchProject46List();

  public void setNewColloquium7(String p);

  public String getNewColloquium7();

  public void selectColloquium7(ValueChangeEvent event);

  public Map<String, String> getColloquium7List();

  public void initColloquium7List();

  public void setNewNews8(String p);

  public String getNewNews8();

  public void selectNews8(ValueChangeEvent event);

  public Map<String, String> getNews8List();

  public void initNews8List();

  public List<Person> getPerson85List();

  public void initPerson85List();

  public List<ResearchProject> getProject80List();

  public void initProject80List();

  public ResearchGroup getResearchGroup();

  public void setResearchGroup(ResearchGroup researchGroup);
}