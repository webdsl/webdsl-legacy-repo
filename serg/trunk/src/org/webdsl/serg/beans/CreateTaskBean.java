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
    Task var41 = new Task();
    task = var41;
    initIssue33List();
    initPerson90List();
    initPerson1058List();
    initProject1158List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue8(Issue issue32)
  { 
    this.getTask().getIssues().remove(issue32);
  }

  public void addIssue8(Issue issue32)
  { 
    this.getTask().getIssues().add(issue32);
  }

  public void removePerson19(Person person89)
  { 
    this.getTask().getAssigned().remove(person89);
  }

  public void addPerson19(Person person89)
  { 
    this.getTask().getAssigned().add(person89);
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
      addIssue8(issue33);
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

  private String newPerson90;

  public void setNewPerson90(String p)
  { 
    newPerson90 = p;
  }

  public String getNewPerson90()
  { 
    return newPerson90;
  }

  public void selectPerson90(ValueChangeEvent event)
  { 
    log.info("selectPerson90" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person90 = em.find(Person.class, id);
      addPerson19(person90);
    }
  }

  @DataModel("person90List") private Map<String, String> person90List;

  public Map<String, String> getPerson90List()
  { 
    return person90List;
  }

  @Factory("person90List") public void initPerson90List()
  { 
    log.info("initPerson90List");
    person90List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person90List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1058List") private List<Person> person1058List;

  public List<Person> getPerson1058List()
  { 
    log.info("getPerson1058List");
    return person1058List;
  }

  @Factory("person1058List") public void initPerson1058List()
  { 
    log.info("initPerson1058List");
    person1058List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1158List") private List<ResearchProject> project1158List;

  public List<ResearchProject> getProject1158List()
  { 
    log.info("getProject1158List");
    return project1158List;
  }

  @Factory("project1158List") public void initProject1158List()
  { 
    log.info("initProject1158List");
    project1158List = em.createQuery("from " + "ResearchProject").getResultList();
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