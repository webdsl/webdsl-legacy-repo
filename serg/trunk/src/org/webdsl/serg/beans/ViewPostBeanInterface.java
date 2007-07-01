package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPostBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPost(Post post);

  public Post getPost();

  public List<Person> getPerson135List();

  public void initPerson135List();

  public List<ResearchProject> getProject126List();

  public void initProject126List();
}