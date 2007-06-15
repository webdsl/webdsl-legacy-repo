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
    initIssue34List();
    initPerson202List();
    initPerson113List();
    initProject104List();
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

  public void removeIssue6(Issue issue33)
  { 
    this.getTask().getIssues().remove(issue33);
  }

  public void addIssue6(Issue issue33)
  { 
    this.getTask().getIssues().add(issue33);
  }

  public void removePerson20(Person person201)
  { 
    this.getTask().getAssigned().remove(person201);
  }

  public void addPerson20(Person person201)
  { 
    this.getTask().getAssigned().add(person201);
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

  private String newIssue34;

  public void setNewIssue34(String p)
  { 
    newIssue34 = p;
  }

  public String getNewIssue34()
  { 
    return newIssue34;
  }

  public void selectIssue34(ValueChangeEvent event)
  { 
    log.info("selectIssue34" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue34 = em.find(Issue.class, id);
      addIssue6(issue34);
    }
  }

  @DataModel("issue34List") private Map<String, String> issue34List;

  public Map<String, String> getIssue34List()
  { 
    return issue34List;
  }

  @Factory("issue34List") public void initIssue34List()
  { 
    log.info("initIssue34List");
    issue34List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue34List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson202;

  public void setNewPerson202(String p)
  { 
    newPerson202 = p;
  }

  public String getNewPerson202()
  { 
    return newPerson202;
  }

  public void selectPerson202(ValueChangeEvent event)
  { 
    log.info("selectPerson202" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person202 = em.find(Person.class, id);
      addPerson20(person202);
    }
  }

  @DataModel("person202List") private Map<String, String> person202List;

  public Map<String, String> getPerson202List()
  { 
    return person202List;
  }

  @Factory("person202List") public void initPerson202List()
  { 
    log.info("initPerson202List");
    person202List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person202List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person113List") private List<Person> person113List;

  public List<Person> getPerson113List()
  { 
    log.info("getPerson113List");
    return person113List;
  }

  @Factory("person113List") public void initPerson113List()
  { 
    log.info("initPerson113List");
    person113List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project104List") private List<ResearchProject> project104List;

  public List<ResearchProject> getProject104List()
  { 
    log.info("getProject104List");
    return project104List;
  }

  @Factory("project104List") public void initProject104List()
  { 
    log.info("initProject104List");
    project104List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}