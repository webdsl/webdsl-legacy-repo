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
    Bug var68 = new Bug();
    bug = var68;
    initIssue31List();
    initPerson199List();
    initPerson109List();
    initProject101List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue5(Issue issue30)
  { 
    this.getBug().getIssues().remove(issue30);
  }

  public void addIssue5(Issue issue30)
  { 
    this.getBug().getIssues().add(issue30);
  }

  public void removePerson19(Person person198)
  { 
    this.getBug().getAssigned().remove(person198);
  }

  public void addPerson19(Person person198)
  { 
    this.getBug().getAssigned().add(person198);
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

  private String newIssue31;

  public void setNewIssue31(String p)
  { 
    newIssue31 = p;
  }

  public String getNewIssue31()
  { 
    return newIssue31;
  }

  public void selectIssue31(ValueChangeEvent event)
  { 
    log.info("selectIssue31" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue31 = em.find(Issue.class, id);
      addIssue5(issue31);
    }
  }

  @DataModel("issue31List") private Map<String, String> issue31List;

  public Map<String, String> getIssue31List()
  { 
    return issue31List;
  }

  @Factory("issue31List") public void initIssue31List()
  { 
    log.info("initIssue31List");
    issue31List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue31List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson199;

  public void setNewPerson199(String p)
  { 
    newPerson199 = p;
  }

  public String getNewPerson199()
  { 
    return newPerson199;
  }

  public void selectPerson199(ValueChangeEvent event)
  { 
    log.info("selectPerson199" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person199 = em.find(Person.class, id);
      addPerson19(person199);
    }
  }

  @DataModel("person199List") private Map<String, String> person199List;

  public Map<String, String> getPerson199List()
  { 
    return person199List;
  }

  @Factory("person199List") public void initPerson199List()
  { 
    log.info("initPerson199List");
    person199List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person199List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person109List") private List<Person> person109List;

  public List<Person> getPerson109List()
  { 
    log.info("getPerson109List");
    return person109List;
  }

  @Factory("person109List") public void initPerson109List()
  { 
    log.info("initPerson109List");
    person109List = em.createQuery("from " + "Person").getResultList();
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