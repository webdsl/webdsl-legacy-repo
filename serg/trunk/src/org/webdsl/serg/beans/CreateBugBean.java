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
    initPerson222List();
    initPerson113List();
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

  public void removePerson21(Person person221)
  { 
    this.getBug().getAssigned().remove(person221);
  }

  public void addPerson21(Person person221)
  { 
    this.getBug().getAssigned().add(person221);
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
    Issue issue37 = (Issue)event.getNewValue();
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

  private String newPerson222;

  public void setNewPerson222(String p)
  { 
    newPerson222 = p;
  }

  public String getNewPerson222()
  { 
    return newPerson222;
  }

  public void selectPerson222(ValueChangeEvent event)
  { 
    log.info("selectPerson222" + ": new value = " + " " + event.getNewValue());
    Person person222 = (Person)event.getNewValue();
  }

  @DataModel("person222List") private Map<String, String> person222List;

  public Map<String, String> getPerson222List()
  { 
    return person222List;
  }

  @Factory("person222List") public void initPerson222List()
  { 
    log.info("initPerson222List");
    person222List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person222List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person113List") private List<Person> person113List;

  public List<Person> getPerson113List()
  { 
    log.info("getPerson113List");
    return person113List;
  }

  @Factory("person113List") public void initPerson113List()
  { 
    log.info("initPerson113List");
    person113List = em.createQuery("from " + "Person").getResultList();
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