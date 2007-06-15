package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson13(Person person184);

  public void addPerson13(Person person184);

  public void removeResearchProject13(ResearchProject researchProject41);

  public void addResearchProject13(ResearchProject researchProject41);

  public void removeColloquium1(Colloquium colloquium7);

  public void addColloquium1(Colloquium colloquium7);

  public void removeNews1(News news7);

  public void addNews1(News news7);

  public String cancel();

  public String save();

  public void setNewPerson185(String p);

  public String getNewPerson185();

  public void selectPerson185(ValueChangeEvent event);

  public Map<String, String> getPerson185List();

  public void initPerson185List();

  public void setNewResearchProject42(String p);

  public String getNewResearchProject42();

  public void selectResearchProject42(ValueChangeEvent event);

  public Map<String, String> getResearchProject42List();

  public void initResearchProject42List();

  public void setNewColloquium8(String p);

  public String getNewColloquium8();

  public void selectColloquium8(ValueChangeEvent event);

  public Map<String, String> getColloquium8List();

  public void initColloquium8List();

  public void setNewNews8(String p);

  public String getNewNews8();

  public void selectNews8(ValueChangeEvent event);

  public Map<String, String> getNews8List();

  public void initNews8List();

  public List<Person> getPerson92List();

  public void initPerson92List();

  public List<ResearchProject> getProject81List();

  public void initProject81List();

  public ResearchGroup getResearchGroup();

  public void setResearchGroup(ResearchGroup researchGroup);
}