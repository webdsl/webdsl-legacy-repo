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

  public void setPerson8(Person person149);

  public void removeResearchProject2(ResearchProject researchProject14);

  public void addResearchProject2(ResearchProject researchProject14);

  public String cancel();

  public String save();

  public void setNewPerson148(String p);

  public String getNewPerson148();

  public void selectPerson148(ValueChangeEvent event);

  public Map<String, String> getPerson148List();

  public void initPerson148List();

  public void setNewResearchProject15(String p);

  public String getNewResearchProject15();

  public void selectResearchProject15(ValueChangeEvent event);

  public Map<String, String> getResearchProject15List();

  public void initResearchProject15List();

  public List<Person> getPerson53List();

  public void initPerson53List();

  public List<ResearchProject> getProject48List();

  public void initProject48List();
}