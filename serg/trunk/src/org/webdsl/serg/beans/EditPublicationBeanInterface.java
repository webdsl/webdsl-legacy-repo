package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPublication(Publication publication);

  public Publication getPublication();

  public void removePerson1(Person person43);

  public void addPerson1(Person person43);

  public void addNewAuthor();

  public void removeResearchProject7(ResearchProject researchProject20);

  public void addResearchProject7(ResearchProject researchProject20);

  public String cancel();

  public String save();

  public void setNewPerson44(String p);

  public String getNewPerson44();

  public void selectPerson44(ValueChangeEvent event);

  public Map<String, String> getPerson44List();

  public void initPerson44List();

  public void setNewResearchProject21(String p);

  public String getNewResearchProject21();

  public void selectResearchProject21(ValueChangeEvent event);

  public Map<String, String> getResearchProject21List();

  public void initResearchProject21List();

  public List<Person> getPerson1034List();

  public void initPerson1034List();

  public List<ResearchProject> getProject1134List();

  public void initProject1134List();

  public Person getNewAuthor0();

  public void setNewAuthor0(Person newAuthor0);
}