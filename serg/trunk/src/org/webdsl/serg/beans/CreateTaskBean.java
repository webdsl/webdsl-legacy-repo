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

@Stateful @Name("createTask") public class CreateTaskBean  implements CreateTaskBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createTask" + ".initalize()");
    Task var74 = new Task();
    task = var74;
    initIssue42List();
    initPerson216List();
    initPerson111List();
    initProject106List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue7(Issue issue41)
  { 
    this.getTask().getIssues().remove(issue41);
  }

  public void addIssue7(Issue issue41)
  { 
    this.getTask().getIssues().add(issue41);
  }

  public void removePerson21(Person person215)
  { 
    this.getTask().getAssigned().remove(person215);
  }

  public void addPerson21(Person person215)
  { 
    this.getTask().getAssigned().add(person215);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getTask());
    return "/" + "viewTask" + ".seam?" + ("task" + "=" + task.getId() + "");
  }

  private String newIssue42;

  public void setNewIssue42(String p)
  { 
    newIssue42 = p;
  }

  public String getNewIssue42()
  { 
    return newIssue42;
  }

  public void selectIssue42(ValueChangeEvent event)
  { 
    log.info("selectIssue42" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue42 = em.find(Issue.class, id);
      addIssue7(issue42);
    }
  }

  @DataModel("issue42List") private Map<String, String> issue42List;

  public Map<String, String> getIssue42List()
  { 
    return issue42List;
  }

  @Factory("issue42List") public void initIssue42List()
  { 
    log.info("initIssue42List");
    issue42List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue42List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson216;

  public void setNewPerson216(String p)
  { 
    newPerson216 = p;
  }

  public String getNewPerson216()
  { 
    return newPerson216;
  }

  public void selectPerson216(ValueChangeEvent event)
  { 
    log.info("selectPerson216" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person216 = em.find(Person.class, id);
      addPerson21(person216);
    }
  }

  @DataModel("person216List") private Map<String, String> person216List;

  public Map<String, String> getPerson216List()
  { 
    return person216List;
  }

  @Factory("person216List") public void initPerson216List()
  { 
    log.info("initPerson216List");
    person216List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person216List.put(p.getName(), p.getId().toString());
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

  @DataModel("project106List") private List<ResearchProject> project106List;

  public List<ResearchProject> getProject106List()
  { 
    log.info("getProject106List");
    return project106List;
  }

  @Factory("project106List") public void initProject106List()
  { 
    log.info("initProject106List");
    project106List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Task task;

  public Task getTask()
  { 
    log.info("getTask");
    return task;
  }

  public void setTask(Task task)
  { 
    log.info("setTask");
    this.task = task;
  }
}