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

@Stateful @Name("viewTask") public class ViewTaskBean  implements ViewTaskBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewTask" + ".initalize()");
    if(taskId == null)
    { 
      log.info("No " + "taskId" + " defined, creating new " + "Task");
      task = new Task();
    }
    else
    { 
      task = em.find(Task.class, taskId);
    }
    initPerson112List();
    initProject107List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("task") private Long taskId;

  private Task task;

  public void setTask(Task task)
  { 
    log.info("setTask");
    this.task = task;
  }

  public Task getTask()
  { 
    log.info("getTask");
    return task;
  }

  @End public String createNewIssue(Issue issue110, java.util.Set<Issue> issues3)
  { 
    Issue var75 = new Issue();
    Issue issue03 = var75;
    issues3.add(issue03);
    em.persist(issue110);
    return "/" + "editIssue" + ".seam?" + ("issue" + "=" + issue03.getId() + "");
  }

  @End public String createNewPerson(Issue issue23, java.util.Set<Person> assigned3)
  { 
    Person var76 = new Person();
    Person person413 = var76;
    assigned3.add(person413);
    em.persist(issue23);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person413.getId() + "");
  }

  @DataModel("person112List") private List<Person> person112List;

  public List<Person> getPerson112List()
  { 
    log.info("getPerson112List");
    return person112List;
  }

  @Factory("person112List") public void initPerson112List()
  { 
    log.info("initPerson112List");
    person112List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project107List") private List<ResearchProject> project107List;

  public List<ResearchProject> getProject107List()
  { 
    log.info("getProject107List");
    return project107List;
  }

  @Factory("project107List") public void initProject107List()
  { 
    log.info("initProject107List");
    project107List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}