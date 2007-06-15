package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateResearchGroupBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePerson13(Person person180);

  public void addPerson13(Person person180);

  public void removeResearchProject13(ResearchProject researchProject36);

  public void addResearchProject13(ResearchProject researchProject36);

  public void removeColloquium1(Colloquium colloquium7);

  public void addColloquium1(Colloquium colloquium7);

  public void removeNews1(News news7);

  public void addNews1(News news7);

  public String cancel();

  public String save();

  public void setNewPerson181(String p);

  public String getNewPerson181();

  public void selectPerson181(ValueChangeEvent event);

  public Map<String, String> getPerson181List();

  public void initPerson181List();

  public void setNewResearchProject37(String p);

  public String getNewResearchProject37();

  public void selectResearchProject37(ValueChangeEvent event);

  public Map<String, String> getResearchProject37List();

  public void initResearchProject37List();

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

  public List<Person> getPerson87List();

  public void initPerson87List();

  public List<ResearchProject> getProject82List();

  public void initProject82List();

  public ResearchGroup getResearchGroup();

  public void setResearchGroup(ResearchGroup researchGroup);
}