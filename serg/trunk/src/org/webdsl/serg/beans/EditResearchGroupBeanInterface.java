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

  public void removePerson6(Person person51);

  public void addPerson6(Person person51);

  public void removeResearchProject9(ResearchProject researchProject26);

  public void addResearchProject9(ResearchProject researchProject26);

  public void removeColloquium0(Colloquium colloquium2);

  public void addColloquium0(Colloquium colloquium2);

  public void removeNews0(News news3);

  public void addNews0(News news3);

  public String cancel();

  public String save();

  public void setNewPerson52(String p);

  public String getNewPerson52();

  public void selectPerson52(ValueChangeEvent event);

  public Map<String, String> getPerson52List();

  public void initPerson52List();

  public void setNewResearchProject27(String p);

  public String getNewResearchProject27();

  public void selectResearchProject27(ValueChangeEvent event);

  public Map<String, String> getResearchProject27List();

  public void initResearchProject27List();

  public void setNewColloquium3(String p);

  public String getNewColloquium3();

  public void selectColloquium3(ValueChangeEvent event);

  public Map<String, String> getColloquium3List();

  public void initColloquium3List();

  public void setNewNews4(String p);

  public String getNewNews4();

  public void selectNews4(ValueChangeEvent event);

  public Map<String, String> getNews4List();

  public void initNews4List();

  public List<Person> getPerson1043List();

  public void initPerson1043List();

  public List<ResearchProject> getProject1143List();

  public void initProject1143List();
}