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
    initIssue32List();
    initPerson182List();
    initPerson105List();
    initProject105List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue7(Issue issue31)
  { 
    this.getTask().getIssues().remove(issue31);
  }

  public void addIssue7(Issue issue31)
  { 
    this.getTask().getIssues().add(issue31);
  }

  public void removePerson21(Person person181)
  { 
    this.getTask().getAssigned().remove(person181);
  }

  public void addPerson21(Person person181)
  { 
    this.getTask().getAssigned().add(person181);
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

  private String newIssue32;

  public void setNewIssue32(String p)
  { 
    newIssue32 = p;
  }

  public String getNewIssue32()
  { 
    return newIssue32;
  }

  public void selectIssue32(ValueChangeEvent event)
  { 
    log.info("selectIssue32" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue32 = em.find(Issue.class, id);
      addIssue7(issue32);
    }
  }

  @DataModel("issue32List") private Map<String, String> issue32List;

  public Map<String, String> getIssue32List()
  { 
    return issue32List;
  }

  @Factory("issue32List") public void initIssue32List()
  { 
    log.info("initIssue32List");
    issue32List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue32List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson182;

  public void setNewPerson182(String p)
  { 
    newPerson182 = p;
  }

  public String getNewPerson182()
  { 
    return newPerson182;
  }

  public void selectPerson182(ValueChangeEvent event)
  { 
    log.info("selectPerson182" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person182 = em.find(Person.class, id);
      addPerson21(person182);
    }
  }

  @DataModel("person182List") private Map<String, String> person182List;

  public Map<String, String> getPerson182List()
  { 
    return person182List;
  }

  @Factory("person182List") public void initPerson182List()
  { 
    log.info("initPerson182List");
    person182List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person182List.put(p.getName(), p.getId().toString());
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