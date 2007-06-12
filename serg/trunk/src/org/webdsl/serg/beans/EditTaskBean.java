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
    initIssue33List();
    initPerson204List();
    initPerson103List();
    initProject103List();
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

  public void removeIssue7(Issue issue32)
  { 
    this.getTask().getIssues().remove(issue32);
  }

  public void addIssue7(Issue issue32)
  { 
    this.getTask().getIssues().add(issue32);
  }

  public void removePerson25(Person person203)
  { 
    this.getTask().getAssigned().remove(person203);
  }

  public void addPerson25(Person person203)
  { 
    this.getTask().getAssigned().add(person203);
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

  private String newIssue33;

  public void setNewIssue33(String p)
  { 
    newIssue33 = p;
  }

  public String getNewIssue33()
  { 
    return newIssue33;
  }

  public void selectIssue33(ValueChangeEvent event)
  { 
    log.info("selectIssue33" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue33 = em.find(Issue.class, id);
      addIssue7(issue33);
    }
  }

  @DataModel("issue33List") private Map<String, String> issue33List;

  public Map<String, String> getIssue33List()
  { 
    return issue33List;
  }

  @Factory("issue33List") public void initIssue33List()
  { 
    log.info("initIssue33List");
    issue33List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue33List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson204;

  public void setNewPerson204(String p)
  { 
    newPerson204 = p;
  }

  public String getNewPerson204()
  { 
    return newPerson204;
  }

  public void selectPerson204(ValueChangeEvent event)
  { 
    log.info("selectPerson204" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person204 = em.find(Person.class, id);
      addPerson25(person204);
    }
  }

  @DataModel("person204List") private Map<String, String> person204List;

  public Map<String, String> getPerson204List()
  { 
    return person204List;
  }

  @Factory("person204List") public void initPerson204List()
  { 
    log.info("initPerson204List");
    person204List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person204List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person103List") private List<Person> person103List;

  public List<Person> getPerson103List()
  { 
    log.info("getPerson103List");
    return person103List;
  }

  @Factory("person103List") public void initPerson103List()
  { 
    log.info("initPerson103List");
    person103List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project103List") private List<ResearchProject> project103List;

  public List<ResearchProject> getProject103List()
  { 
    log.info("getProject103List");
    return project103List;
  }

  @Factory("project103List") public void initProject103List()
  { 
    log.info("initProject103List");
    project103List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}