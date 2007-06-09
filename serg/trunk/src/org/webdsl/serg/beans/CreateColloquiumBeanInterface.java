package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateColloquiumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePresentation1(Presentation presentation5);

  public void addPresentation1(Presentation presentation5);

  public void setPerson10(Person person27);

  public String cancel();

  public String save();

  public void setNewPresentation6(String p);

  public String getNewPresentation6();

  public void selectPresentation6(ValueChangeEvent event);

  public Map<String, String> getPresentation6List();

  public void initPresentation6List();

  public void setNewPerson26(String p);

  public String getNewPerson26();

  public void selectPerson26(ValueChangeEvent event);

  public Map<String, String> getPerson26List();

  public void initPerson26List();

  public List<Person> getPerson1028List();

  public void initPerson1028List();

  public List<ResearchProject> getProject1128List();

  public void initProject1128List();

  public Colloquium getColloquium();

  public void setColloquium(Colloquium colloquium);
}