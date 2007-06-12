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
    initIssue30List();
    initPerson201List();
    initPerson100List();
    initProject100List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue6(Issue issue29)
  { 
    this.getBug().getIssues().remove(issue29);
  }

  public void addIssue6(Issue issue29)
  { 
    this.getBug().getIssues().add(issue29);
  }

  public void removePerson24(Person person200)
  { 
    this.getBug().getAssigned().remove(person200);
  }

  public void addPerson24(Person person200)
  { 
    this.getBug().getAssigned().add(person200);
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

  private String newIssue30;

  public void setNewIssue30(String p)
  { 
    newIssue30 = p;
  }

  public String getNewIssue30()
  { 
    return newIssue30;
  }

  public void selectIssue30(ValueChangeEvent event)
  { 
    log.info("selectIssue30" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue30 = em.find(Issue.class, id);
      addIssue6(issue30);
    }
  }

  @DataModel("issue30List") private Map<String, String> issue30List;

  public Map<String, String> getIssue30List()
  { 
    return issue30List;
  }

  @Factory("issue30List") public void initIssue30List()
  { 
    log.info("initIssue30List");
    issue30List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue30List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson201;

  public void setNewPerson201(String p)
  { 
    newPerson201 = p;
  }

  public String getNewPerson201()
  { 
    return newPerson201;
  }

  public void selectPerson201(ValueChangeEvent event)
  { 
    log.info("selectPerson201" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person201 = em.find(Person.class, id);
      addPerson24(person201);
    }
  }

  @DataModel("person201List") private Map<String, String> person201List;

  public Map<String, String> getPerson201List()
  { 
    return person201List;
  }

  @Factory("person201List") public void initPerson201List()
  { 
    log.info("initPerson201List");
    person201List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person201List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person100List") private List<Person> person100List;

  public List<Person> getPerson100List()
  { 
    log.info("getPerson100List");
    return person100List;
  }

  @Factory("person100List") public void initPerson100List()
  { 
    log.info("initPerson100List");
    person100List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project100List") private List<ResearchProject> project100List;

  public List<ResearchProject> getProject100List()
  { 
    log.info("getProject100List");
    return project100List;
  }

  @Factory("project100List") public void initProject100List()
  { 
    log.info("initProject100List");
    project100List = em.createQuery("from " + "ResearchProject").getResultList();
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