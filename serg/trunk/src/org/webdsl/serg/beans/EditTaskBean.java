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
    initIssue30List();
    initPerson198List();
    initPerson105List();
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

  public void removeIssue6(Issue issue29)
  { 
    this.getTask().getIssues().remove(issue29);
  }

  public void addIssue6(Issue issue29)
  { 
    this.getTask().getIssues().add(issue29);
  }

  public void removePerson20(Person person197)
  { 
    this.getTask().getAssigned().remove(person197);
  }

  public void addPerson20(Person person197)
  { 
    this.getTask().getAssigned().add(person197);
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

  private String newIssue30;

  public void setNewIssue30(String p)
  { 
    newIssue30 = p;
  }

  public String getNewIssue30()
  { 
    return newIssue30;
  }

  public void selectIssue30(ValueChangeEvent event)
  { 
    log.info("selectIssue30" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue30 = em.find(Issue.class, id);
      addIssue6(issue30);
    }
  }

  @DataModel("issue30List") private Map<String, String> issue30List;

  public Map<String, String> getIssue30List()
  { 
    return issue30List;
  }

  @Factory("issue30List") public void initIssue30List()
  { 
    log.info("initIssue30List");
    issue30List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue30List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson198;

  public void setNewPerson198(String p)
  { 
    newPerson198 = p;
  }

  public String getNewPerson198()
  { 
    return newPerson198;
  }

  public void selectPerson198(ValueChangeEvent event)
  { 
    log.info("selectPerson198" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person198 = em.find(Person.class, id);
      addPerson20(person198);
    }
  }

  @DataModel("person198List") private Map<String, String> person198List;

  public Map<String, String> getPerson198List()
  { 
    return person198List;
  }

  @Factory("person198List") public void initPerson198List()
  { 
    log.info("initPerson198List");
    person198List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person198List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person105List") private List<Person> person105List;

  public List<Person> getPerson105List()
  { 
    log.info("getPerson105List");
    return person105List;
  }

  @Factory("person105List") public void initPerson105List()
  { 
    log.info("initPerson105List");
    person105List = em.createQuery("from " + "Person").getResultList();
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