package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllDiscussionBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeDiscussion(Discussion discussion4);

  public List<Person> getPerson115List();

  public void initPerson115List();

  public List<ResearchProject> getProject115List();

  public void initProject115List();

  public List<Discussion> getDiscussion3List();

  public void initDiscussion3List();
}