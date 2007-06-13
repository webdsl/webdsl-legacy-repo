package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson13(Person person162);

  public void addPerson13(Person person162);

  public void removeResearchProject13(ResearchProject researchProject36);

  public void addResearchProject13(ResearchProject researchProject36);

  public void removeColloquium1(Colloquium colloquium6);

  public void addColloquium1(Colloquium colloquium6);

  public void removeNews1(News news7);

  public void addNews1(News news7);

  public String cancel();

  public String save();

  public void setNewPerson163(String p);

  public String getNewPerson163();

  public void selectPerson163(ValueChangeEvent event);

  public Map<String, String> getPerson163List();

  public void initPerson163List();

  public void setNewResearchProject37(String p);

  public String getNewResearchProject37();

  public void selectResearchProject37(ValueChangeEvent event);

  public Map<String, String> getResearchProject37List();

  public void initResearchProject37List();

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

  public List<Person> getPerson86List();

  public void initPerson86List();

  public List<ResearchProject> getProject81List();

  public void initProject81List();

  public ResearchGroup getResearchGroup();

  public void setResearchGroup(ResearchGroup researchGroup);
}