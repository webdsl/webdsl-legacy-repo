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

  public List<Person> getPerson18List();

  public void initPerson18List();

  public List<ResearchProject> getProject17List();

  public void initProject17List();

  public List<Publication> getPub6List();

  public void initPub6List();
}