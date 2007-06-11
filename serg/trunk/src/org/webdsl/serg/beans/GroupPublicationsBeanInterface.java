package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface GroupPublicationsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setGroup(ResearchGroup group);

  public ResearchGroup getGroup();

  public List<Person> getPerson1010List();

  public void initPerson1010List();

  public List<ResearchProject> getProject1110List();

  public void initProject1110List();

  public List<Publication> getPub5List();

  public void initPub5List();
}