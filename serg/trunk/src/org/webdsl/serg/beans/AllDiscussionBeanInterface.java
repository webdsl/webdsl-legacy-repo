package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllDiscussionBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeDiscussion(Discussion discussion5);

  public List<Person> getPerson128List();

  public void initPerson128List();

  public List<ResearchProject> getProject119List();

  public void initProject119List();

  public List<Discussion> getDiscussion4List();

  public void initDiscussion4List();
}