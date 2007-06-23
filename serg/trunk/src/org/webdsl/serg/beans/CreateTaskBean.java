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
    Task var79 = new Task();
    task = var79;
    initIssue42List();
    initPerson227List();
    initPerson117List();
    initProject110List();
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

  public void removePerson23(Person person226)
  { 
    this.getTask().getAssigned().remove(person226);
  }

  public void addPerson23(Person person226)
  { 
    this.getTask().getAssigned().add(person226);
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
    Issue issue42 = (Issue)event.getNewValue();
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

  private String newPerson227;

  public void setNewPerson227(String p)
  { 
    newPerson227 = p;
  }

  public String getNewPerson227()
  { 
    return newPerson227;
  }

  public void selectPerson227(ValueChangeEvent event)
  { 
    log.info("selectPerson227" + ": new value = " + " " + event.getNewValue());
    Person person227 = (Person)event.getNewValue();
  }

  @DataModel("person227List") private Map<String, String> person227List;

  public Map<String, String> getPerson227List()
  { 
    return person227List;
  }

  @Factory("person227List") public void initPerson227List()
  { 
    log.info("initPerson227List");
    person227List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person227List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person117List") private List<Person> person117List;

  public List<Person> getPerson117List()
  { 
    log.info("getPerson117List");
    return person117List;
  }

  @Factory("person117List") public void initPerson117List()
  { 
    log.info("initPerson117List");
    person117List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project110List") private List<ResearchProject> project110List;

  public List<ResearchProject> getProject110List()
  { 
    log.info("getProject110List");
    return project110List;
  }

  @Factory("project110List") public void initProject110List()
  { 
    log.info("initProject110List");
    project110List = em.createQuery("from " + "ResearchProject").getResultList();
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