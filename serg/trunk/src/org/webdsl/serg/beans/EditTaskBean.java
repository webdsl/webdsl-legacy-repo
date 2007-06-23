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

@Stateful @Name("editTask") public class EditTaskBean  implements EditTaskBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editTask" + ".initalize()");
    if(taskId == null)
    { 
      log.info("No " + "taskId" + " defined, creating new " + "Task");
      task = new Task();
    }
    else
    { 
      task = em.find(Task.class, taskId);
    }
    initIssue40List();
    initPerson225List();
    initPerson116List();
    initProject109List();
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

  public void removeIssue6(Issue issue39)
  { 
    this.getTask().getIssues().remove(issue39);
  }

  public void addIssue6(Issue issue39)
  { 
    this.getTask().getIssues().add(issue39);
  }

  public void removePerson22(Person person224)
  { 
    this.getTask().getAssigned().remove(person224);
  }

  public void addPerson22(Person person224)
  { 
    this.getTask().getAssigned().add(person224);
  }

  @End public String cancel()
  { 
    return "/" + "viewTask" + ".seam?" + ("task" + "=" + task.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getTask());
    return "/" + "viewTask" + ".seam?" + ("task" + "=" + task.getId() + "");
  }

  private String newIssue40;

  public void setNewIssue40(String p)
  { 
    newIssue40 = p;
  }

  public String getNewIssue40()
  { 
    return newIssue40;
  }

  public void selectIssue40(ValueChangeEvent event)
  { 
    log.info("selectIssue40" + ": new value = " + " " + event.getNewValue());
    Issue issue40 = (Issue)event.getNewValue();
  }

  @DataModel("issue40List") private Map<String, String> issue40List;

  public Map<String, String> getIssue40List()
  { 
    return issue40List;
  }

  @Factory("issue40List") public void initIssue40List()
  { 
    log.info("initIssue40List");
    issue40List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue40List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson225;

  public void setNewPerson225(String p)
  { 
    newPerson225 = p;
  }

  public String getNewPerson225()
  { 
    return newPerson225;
  }

  public void selectPerson225(ValueChangeEvent event)
  { 
    log.info("selectPerson225" + ": new value = " + " " + event.getNewValue());
    Person person225 = (Person)event.getNewValue();
  }

  @DataModel("person225List") private Map<String, String> person225List;

  public Map<String, String> getPerson225List()
  { 
    return person225List;
  }

  @Factory("person225List") public void initPerson225List()
  { 
    log.info("initPerson225List");
    person225List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person225List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person116List") private List<Person> person116List;

  public List<Person> getPerson116List()
  { 
    log.info("getPerson116List");
    return person116List;
  }

  @Factory("person116List") public void initPerson116List()
  { 
    log.info("initPerson116List");
    person116List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project109List") private List<ResearchProject> project109List;

  public List<ResearchProject> getProject109List()
  { 
    log.info("getProject109List");
    return project109List;
  }

  @Factory("project109List") public void initProject109List()
  { 
    log.info("initProject109List");
    project109List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}