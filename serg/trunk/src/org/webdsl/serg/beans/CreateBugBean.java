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

@Stateful @Name("createBug") public class CreateBugBean  implements CreateBugBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createBug" + ".initalize()");
    Bug var73 = new Bug();
    bug = var73;
    initIssue37List();
    initPerson212List();
    initPerson107List();
    initProject102List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue5(Issue issue36)
  { 
    this.getBug().getIssues().remove(issue36);
  }

  public void addIssue5(Issue issue36)
  { 
    this.getBug().getIssues().add(issue36);
  }

  public void removePerson19(Person person211)
  { 
    this.getBug().getAssigned().remove(person211);
  }

  public void addPerson19(Person person211)
  { 
    this.getBug().getAssigned().add(person211);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getBug());
    return "/" + "viewBug" + ".seam?" + ("bug" + "=" + bug.getId() + "");
  }

  private String newIssue37;

  public void setNewIssue37(String p)
  { 
    newIssue37 = p;
  }

  public String getNewIssue37()
  { 
    return newIssue37;
  }

  public void selectIssue37(ValueChangeEvent event)
  { 
    log.info("selectIssue37" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue37 = em.find(Issue.class, id);
      addIssue5(issue37);
    }
  }

  @DataModel("issue37List") private Map<String, String> issue37List;

  public Map<String, String> getIssue37List()
  { 
    return issue37List;
  }

  @Factory("issue37List") public void initIssue37List()
  { 
    log.info("initIssue37List");
    issue37List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue37List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson212;

  public void setNewPerson212(String p)
  { 
    newPerson212 = p;
  }

  public String getNewPerson212()
  { 
    return newPerson212;
  }

  public void selectPerson212(ValueChangeEvent event)
  { 
    log.info("selectPerson212" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person212 = em.find(Person.class, id);
      addPerson19(person212);
    }
  }

  @DataModel("person212List") private Map<String, String> person212List;

  public Map<String, String> getPerson212List()
  { 
    return person212List;
  }

  @Factory("person212List") public void initPerson212List()
  { 
    log.info("initPerson212List");
    person212List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person212List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person107List") private List<Person> person107List;

  public List<Person> getPerson107List()
  { 
    log.info("getPerson107List");
    return person107List;
  }

  @Factory("person107List") public void initPerson107List()
  { 
    log.info("initPerson107List");
    person107List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project102List") private List<ResearchProject> project102List;

  public List<ResearchProject> getProject102List()
  { 
    log.info("getProject102List");
    return project102List;
  }

  @Factory("project102List") public void initProject102List()
  { 
    log.info("initProject102List");
    project102List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Bug bug;

  public Bug getBug()
  { 
    log.info("getBug");
    return bug;
  }

  public void setBug(Bug bug)
  { 
    log.info("setBug");
    this.bug = bug;
  }
}