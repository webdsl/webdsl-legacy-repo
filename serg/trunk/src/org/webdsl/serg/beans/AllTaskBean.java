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
    initPerson108List();
    initProject108List();
    initTask3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeTask(Task task4)
  { 
    em.remove(task4);
  }

  @DataModel("person108List") private List<Person> person108List;

  public List<Person> getPerson108List()
  { 
    log.info("getPerson108List");
    return person108List;
  }

  @Factory("person108List") public void initPerson108List()
  { 
    log.info("initPerson108List");
    person108List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project108List") private List<ResearchProject> project108List;

  public List<ResearchProject> getProject108List()
  { 
    log.info("getProject108List");
    return project108List;
  }

  @Factory("project108List") public void initProject108List()
  { 
    log.info("initProject108List");
    project108List = em.createQuery("from " + "ResearchProject").getResultList();
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