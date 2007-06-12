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
    Task var53 = new Task();
    task = var53;
    initIssue35List();
    initPerson206List();
    initPerson104List();
    initProject104List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue8(Issue issue34)
  { 
    this.getTask().getIssues().remove(issue34);
  }

  public void addIssue8(Issue issue34)
  { 
    this.getTask().getIssues().add(issue34);
  }

  public void removePerson26(Person person205)
  { 
    this.getTask().getAssigned().remove(person205);
  }

  public void addPerson26(Person person205)
  { 
    this.getTask().getAssigned().add(person205);
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

  private String newIssue35;

  public void setNewIssue35(String p)
  { 
    newIssue35 = p;
  }

  public String getNewIssue35()
  { 
    return newIssue35;
  }

  public void selectIssue35(ValueChangeEvent event)
  { 
    log.info("selectIssue35" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue35 = em.find(Issue.class, id);
      addIssue8(issue35);
    }
  }

  @DataModel("issue35List") private Map<String, String> issue35List;

  public Map<String, String> getIssue35List()
  { 
    return issue35List;
  }

  @Factory("issue35List") public void initIssue35List()
  { 
    log.info("initIssue35List");
    issue35List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue35List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson206;

  public void setNewPerson206(String p)
  { 
    newPerson206 = p;
  }

  public String getNewPerson206()
  { 
    return newPerson206;
  }

  public void selectPerson206(ValueChangeEvent event)
  { 
    log.info("selectPerson206" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person206 = em.find(Person.class, id);
      addPerson26(person206);
    }
  }

  @DataModel("person206List") private Map<String, String> person206List;

  public Map<String, String> getPerson206List()
  { 
    return person206List;
  }

  @Factory("person206List") public void initPerson206List()
  { 
    log.info("initPerson206List");
    person206List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person206List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person104List") private List<Person> person104List;

  public List<Person> getPerson104List()
  { 
    log.info("getPerson104List");
    return person104List;
  }

  @Factory("person104List") public void initPerson104List()
  { 
    log.info("initPerson104List");
    person104List = em.createQuery("from " + "Person").getResultList();
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