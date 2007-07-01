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

  public void removePerson14(Person person219);

  public void addPerson14(Person person219);

  public void removeResearchProject14(ResearchProject researchProject52);

  public void addResearchProject14(ResearchProject researchProject52);

  public void removeColloquium0(Colloquium colloquium7);

  public void addColloquium0(Colloquium colloquium7);

  public void removeNews0(News news6);

  public void addNews0(News news6);

  public String cancel();

  public String save();

  public void setNewPerson220(String p);

  public String getNewPerson220();

  public void selectPerson220(ValueChangeEvent event);

  public Map<String, String> getPerson220List();

  public void initPerson220List();

  public void setNewResearchProject53(String p);

  public String getNewResearchProject53();

  public void selectResearchProject53(ValueChangeEvent event);

  public Map<String, String> getResearchProject53List();

  public void initResearchProject53List();

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

  public List<Person> getPerson98List();

  public void initPerson98List();

  public List<ResearchProject> getProject85List();

  public void initProject85List();
}