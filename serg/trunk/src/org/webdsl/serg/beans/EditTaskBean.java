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
    initIssue31List();
    initPerson88List();
    initPerson1057List();
    initProject1157List();
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

  public void removeIssue7(Issue issue30)
  { 
    this.getTask().getIssues().remove(issue30);
  }

  public void addIssue7(Issue issue30)
  { 
    this.getTask().getIssues().add(issue30);
  }

  public void removePerson18(Person person87)
  { 
    this.getTask().getAssigned().remove(person87);
  }

  public void addPerson18(Person person87)
  { 
    this.getTask().getAssigned().add(person87);
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

  private String newIssue31;

  public void setNewIssue31(String p)
  { 
    newIssue31 = p;
  }

  public String getNewIssue31()
  { 
    return newIssue31;
  }

  public void selectIssue31(ValueChangeEvent event)
  { 
    log.info("selectIssue31" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue31 = em.find(Issue.class, id);
      addIssue7(issue31);
    }
  }

  @DataModel("issue31List") private Map<String, String> issue31List;

  public Map<String, String> getIssue31List()
  { 
    return issue31List;
  }

  @Factory("issue31List") public void initIssue31List()
  { 
    log.info("initIssue31List");
    issue31List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue31List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson88;

  public void setNewPerson88(String p)
  { 
    newPerson88 = p;
  }

  public String getNewPerson88()
  { 
    return newPerson88;
  }

  public void selectPerson88(ValueChangeEvent event)
  { 
    log.info("selectPerson88" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person88 = em.find(Person.class, id);
      addPerson18(person88);
    }
  }

  @DataModel("person88List") private Map<String, String> person88List;

  public Map<String, String> getPerson88List()
  { 
    return person88List;
  }

  @Factory("person88List") public void initPerson88List()
  { 
    log.info("initPerson88List");
    person88List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person88List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1057List") private List<Person> person1057List;

  public List<Person> getPerson1057List()
  { 
    log.info("getPerson1057List");
    return person1057List;
  }

  @Factory("person1057List") public void initPerson1057List()
  { 
    log.info("initPerson1057List");
    person1057List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1157List") private List<ResearchProject> project1157List;

  public List<ResearchProject> getProject1157List()
  { 
    log.info("getProject1157List");
    return project1157List;
  }

  @Factory("project1157List") public void initProject1157List()
  { 
    log.info("initProject1157List");
    project1157List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}