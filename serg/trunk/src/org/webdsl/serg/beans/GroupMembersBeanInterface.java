package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface GroupMembersBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setGroup(ResearchGroup group);

  public ResearchGroup getGroup();

  public List<Person> getPerson108List();

  public void initPerson108List();

  public List<ResearchProject> getProject118List();

  public void initProject118List();

  public List<Person> getPerson7List();

  public void initPerson7List();
}