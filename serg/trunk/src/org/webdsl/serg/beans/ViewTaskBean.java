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
    initPerson120List();
    initProject111List();
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
    Issue var80 = new Issue();
    Issue issue03 = var80;
    issues3.add(issue03);
    em.persist(issue110);
    return "/" + "editIssue" + ".seam?" + ("issue" + "=" + issue03.getId() + "");
  }

  @End public String createNewPerson(Issue issue23, java.util.Set<Person> assigned3)
  { 
    Person var81 = new Person();
    Person person513 = var81;
    assigned3.add(person513);
    em.persist(issue23);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person513.getId() + "");
  }

  @DataModel("person120List") private List<Person> person120List;

  public List<Person> getPerson120List()
  { 
    log.info("getPerson120List");
    return person120List;
  }

  @Factory("person120List") public void initPerson120List()
  { 
    log.info("initPerson120List");
    person120List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project111List") private List<ResearchProject> project111List;

  public List<ResearchProject> getProject111List()
  { 
    log.info("getProject111List");
    return project111List;
  }

  @Factory("project111List") public void initProject111List()
  { 
    log.info("initProject111List");
    project111List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}