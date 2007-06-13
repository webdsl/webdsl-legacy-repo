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
    Bug var52 = new Bug();
    bug = var52;
    initIssue27List();
    initPerson177List();
    initPerson101List();
    initProject101List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue5(Issue issue26)
  { 
    this.getBug().getIssues().remove(issue26);
  }

  public void addIssue5(Issue issue26)
  { 
    this.getBug().getIssues().add(issue26);
  }

  public void removePerson19(Person person176)
  { 
    this.getBug().getAssigned().remove(person176);
  }

  public void addPerson19(Person person176)
  { 
    this.getBug().getAssigned().add(person176);
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

  private String newIssue27;

  public void setNewIssue27(String p)
  { 
    newIssue27 = p;
  }

  public String getNewIssue27()
  { 
    return newIssue27;
  }

  public void selectIssue27(ValueChangeEvent event)
  { 
    log.info("selectIssue27" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue27 = em.find(Issue.class, id);
      addIssue5(issue27);
    }
  }

  @DataModel("issue27List") private Map<String, String> issue27List;

  public Map<String, String> getIssue27List()
  { 
    return issue27List;
  }

  @Factory("issue27List") public void initIssue27List()
  { 
    log.info("initIssue27List");
    issue27List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue27List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson177;

  public void setNewPerson177(String p)
  { 
    newPerson177 = p;
  }

  public String getNewPerson177()
  { 
    return newPerson177;
  }

  public void selectPerson177(ValueChangeEvent event)
  { 
    log.info("selectPerson177" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person177 = em.find(Person.class, id);
      addPerson19(person177);
    }
  }

  @DataModel("person177List") private Map<String, String> person177List;

  public Map<String, String> getPerson177List()
  { 
    return person177List;
  }

  @Factory("person177List") public void initPerson177List()
  { 
    log.info("initPerson177List");
    person177List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person177List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person101List") private List<Person> person101List;

  public List<Person> getPerson101List()
  { 
    log.info("getPerson101List");
    return person101List;
  }

  @Factory("person101List") public void initPerson101List()
  { 
    log.info("initPerson101List");
    person101List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project101List") private List<ResearchProject> project101List;

  public List<ResearchProject> getProject101List()
  { 
    log.info("getProject101List");
    return project101List;
  }

  @Factory("project101List") public void initProject101List()
  { 
    log.info("initProject101List");
    project101List = em.createQuery("from " + "ResearchProject").getResultList();
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