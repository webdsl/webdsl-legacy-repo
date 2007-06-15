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
    Task var71 = new Task();
    task = var71;
    initIssue36List();
    initPerson204List();
    initPerson114List();
    initProject105List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue7(Issue issue35)
  { 
    this.getTask().getIssues().remove(issue35);
  }

  public void addIssue7(Issue issue35)
  { 
    this.getTask().getIssues().add(issue35);
  }

  public void removePerson21(Person person203)
  { 
    this.getTask().getAssigned().remove(person203);
  }

  public void addPerson21(Person person203)
  { 
    this.getTask().getAssigned().add(person203);
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

  private String newIssue36;

  public void setNewIssue36(String p)
  { 
    newIssue36 = p;
  }

  public String getNewIssue36()
  { 
    return newIssue36;
  }

  public void selectIssue36(ValueChangeEvent event)
  { 
    log.info("selectIssue36" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue36 = em.find(Issue.class, id);
      addIssue7(issue36);
    }
  }

  @DataModel("issue36List") private Map<String, String> issue36List;

  public Map<String, String> getIssue36List()
  { 
    return issue36List;
  }

  @Factory("issue36List") public void initIssue36List()
  { 
    log.info("initIssue36List");
    issue36List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue36List.put(p.getName(), p.getId().toString());
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
      addPerson21(person204);
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

  @DataModel("person114List") private List<Person> person114List;

  public List<Person> getPerson114List()
  { 
    log.info("getPerson114List");
    return person114List;
  }

  @Factory("person114List") public void initPerson114List()
  { 
    log.info("initPerson114List");
    person114List = em.createQuery("from " + "Person").getResultList();
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