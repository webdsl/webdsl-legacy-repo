package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.faces.event.ValueChangeEvent;
import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.ejb.Remove;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Factory;
import org.webdsl.serg.domain.*;

@Stateful @Name("allTask") public class AllTaskBean  implements AllTaskBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allTask" + ".initalize()");
    initPerson119List();
    initProject112List();
    initTask3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeTask(Task task4)
  { 
    em.remove(task4);
  }

  @DataModel("person119List") private List<Person> person119List;

  public List<Person> getPerson119List()
  { 
    log.info("getPerson119List");
    return person119List;
  }

  @Factory("person119List") public void initPerson119List()
  { 
    log.info("initPerson119List");
    person119List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project112List") private List<ResearchProject> project112List;

  public List<ResearchProject> getProject112List()
  { 
    log.info("getProject112List");
    return project112List;
  }

  @Factory("project112List") public void initProject112List()
  { 
    log.info("initProject112List");
    project112List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("task3List") private List<Task> task3List;

  public List<Task> getTask3List()
  { 
    log.info("getTask3List");
    return task3List;
  }

  @Factory("task3List") public void initTask3List()
  { 
    log.info("initTask3List");
    task3List = em.createQuery("from " + "Task").getResultList();
  }
}