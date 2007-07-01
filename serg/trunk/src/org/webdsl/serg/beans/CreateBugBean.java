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
    Bug var76 = new Bug();
    bug = var76;
    initIssue37List();
    initPerson236List();
    initPerson115List();
    initProject106List();
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

  public void removePerson21(Person person235)
  { 
    this.getBug().getAssigned().remove(person235);
  }

  public void addPerson21(Person person235)
  { 
    this.getBug().getAssigned().add(person235);
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

  private String newPerson236;

  public void setNewPerson236(String p)
  { 
    newPerson236 = p;
  }

  public String getNewPerson236()
  { 
    return newPerson236;
  }

  public void selectPerson236(ValueChangeEvent event)
  { 
    log.info("selectPerson236" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person236 = em.find(Person.class, id);
      addPerson21(person236);
    }
  }

  @DataModel("person236List") private Map<String, String> person236List;

  public Map<String, String> getPerson236List()
  { 
    return person236List;
  }

  @Factory("person236List") public void initPerson236List()
  { 
    log.info("initPerson236List");
    person236List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person236List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person115List") private List<Person> person115List;

  public List<Person> getPerson115List()
  { 
    log.info("getPerson115List");
    return person115List;
  }

  @Factory("person115List") public void initPerson115List()
  { 
    log.info("initPerson115List");
    person115List = em.createQuery("from " + "Person").getResultList();
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