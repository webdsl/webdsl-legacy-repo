package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setColloquium(Colloquium colloquium);

  public Colloquium getColloquium();

  public void setPerson7(Person person162);

  public void setResearchGroup0(ResearchGroup researchGroup9);

  public void removeResearchProject0(ResearchProject researchProject15);

  public void addResearchProject0(ResearchProject researchProject15);

  public void removePresentation0(Presentation presentation7);

  public void addPresentation0(Presentation presentation7);

  public String cancel();

  public String save();

  public void setNewPerson161(String p);

  public String getNewPerson161();

  public void selectPerson161(ValueChangeEvent event);

  public Map<String, String> getPerson161List();

  public void initPerson161List();

  public void setNewResearchGroup8(String p);

  public String getNewResearchGroup8();

  public void selectResearchGroup8(ValueChangeEvent event);

  public Map<String, String> getResearchGroup8List();

  public void initResearchGroup8List();

  public void setNewResearchProject16(String p);

  public String getNewResearchProject16();

  public void selectResearchProject16(ValueChangeEvent event);

  public Map<String, String> getResearchProject16List();

  public void initResearchProject16List();

  public void setNewPresentation8(String p);

  public String getNewPresentation8();

  public void selectPresentation8(ValueChangeEvent event);

  public Map<String, String> getPresentation8List();

  public void initPresentation8List();

  public List<Person> getPerson57List();

  public void initPerson57List();

  public List<ResearchProject> getProject46List();

  public void initProject46List();
}