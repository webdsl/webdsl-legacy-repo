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
    initPerson215List();
    initPerson111List();
    initProject105List();
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

  public void removePerson20(Person person214)
  { 
    this.getTask().getAssigned().remove(person214);
  }

  public void addPerson20(Person person214)
  { 
    this.getTask().getAssigned().add(person214);
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue40 = em.find(Issue.class, id);
      addIssue6(issue40);
    }
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

  private String newPerson215;

  public void setNewPerson215(String p)
  { 
    newPerson215 = p;
  }

  public String getNewPerson215()
  { 
    return newPerson215;
  }

  public void selectPerson215(ValueChangeEvent event)
  { 
    log.info("selectPerson215" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person215 = em.find(Person.class, id);
      addPerson20(person215);
    }
  }

  @DataModel("person215List") private Map<String, String> person215List;

  public Map<String, String> getPerson215List()
  { 
    return person215List;
  }

  @Factory("person215List") public void initPerson215List()
  { 
    log.info("initPerson215List");
    person215List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person215List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person111List") private List<Person> person111List;

  public List<Person> getPerson111List()
  { 
    log.info("getPerson111List");
    return person111List;
  }

  @Factory("person111List") public void initPerson111List()
  { 
    log.info("initPerson111List");
    person111List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project105List") private List<ResearchProject> project105List;

  public List<ResearchProject> getProject105List()
  { 
    log.info("getProject105List");
    return project105List;
  }

  @Factory("project105List") public void initProject105List()
  { 
    log.info("initProject105List");
    project105List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}