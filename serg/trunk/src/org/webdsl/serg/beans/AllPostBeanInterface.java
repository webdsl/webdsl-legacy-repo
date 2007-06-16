package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface AllPostBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removePost(Post post4);

  public List<Person> getPerson129List();

  public void initPerson129List();

  public List<ResearchProject> getProject123List();

  public void initProject123List();

  public List<Post> getPost3List();

  public void initPost3List();
}