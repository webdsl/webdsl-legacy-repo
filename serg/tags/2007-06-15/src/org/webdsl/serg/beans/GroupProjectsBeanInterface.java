package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface GroupProjectsBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setGroup(ResearchGroup group);

  public ResearchGroup getGroup();

  public List<Person> getPerson23List();

  public void initPerson23List();

  public List<ResearchProject> getProject15List();

  public void initProject15List();
}