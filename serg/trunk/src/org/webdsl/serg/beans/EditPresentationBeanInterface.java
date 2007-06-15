package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPresentationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPresentation(Presentation presentation);

  public Presentation getPresentation();

  public void setPerson9(Person person145);

  public void removeResearchProject2(ResearchProject researchProject9);

  public void addResearchProject2(ResearchProject researchProject9);

  public String cancel();

  public String save();

  public void setNewPerson144(String p);

  public String getNewPerson144();

  public void selectPerson144(ValueChangeEvent event);

  public Map<String, String> getPerson144List();

  public void initPerson144List();

  public void setNewResearchProject10(String p);

  public String getNewResearchProject10();

  public void selectResearchProject10(ValueChangeEvent event);

  public Map<String, String> getResearchProject10List();

  public void initResearchProject10List();

  public List<Person> getPerson54List();

  public void initPerson54List();

  public List<ResearchProject> getProject49List();

  public void initProject49List();
}