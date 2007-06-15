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

  public void removePerson12(Person person183);

  public void addPerson12(Person person183);

  public void removeResearchProject12(ResearchProject researchProject45);

  public void addResearchProject12(ResearchProject researchProject45);

  public void removeColloquium0(Colloquium colloquium7);

  public void addColloquium0(Colloquium colloquium7);

  public void removeNews0(News news6);

  public void addNews0(News news6);

  public String cancel();

  public String save();

  public void setNewPerson184(String p);

  public String getNewPerson184();

  public void selectPerson184(ValueChangeEvent event);

  public Map<String, String> getPerson184List();

  public void initPerson184List();

  public void setNewResearchProject46(String p);

  public String getNewResearchProject46();

  public void selectResearchProject46(ValueChangeEvent event);

  public Map<String, String> getResearchProject46List();

  public void initResearchProject46List();

  public void setNewColloquium8(String p);

  public String getNewColloquium8();

  public void selectColloquium8(ValueChangeEvent event);

  public Map<String, String> getColloquium8List();

  public void initColloquium8List();

  public void setNewNews7(String p);

  public String getNewNews7();

  public void selectNews7(ValueChangeEvent event);

  public Map<String, String> getNews7List();

  public void initNews7List();

  public List<Person> getPerson91List();

  public void initPerson91List();

  public List<ResearchProject> getProject81List();

  public void initProject81List();
}