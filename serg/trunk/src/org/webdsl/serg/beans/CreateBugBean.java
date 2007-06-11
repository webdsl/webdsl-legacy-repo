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
    Bug var40 = new Bug();
    bug = var40;
    initIssue28List();
    initPerson85List();
    initPerson1055List();
    initProject1155List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue6(Issue issue27)
  { 
    this.getBug().getIssues().remove(issue27);
  }

  public void addIssue6(Issue issue27)
  { 
    this.getBug().getIssues().add(issue27);
  }

  public void removePerson17(Person person84)
  { 
    this.getBug().getAssigned().remove(person84);
  }

  public void addPerson17(Person person84)
  { 
    this.getBug().getAssigned().add(person84);
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

  private String newIssue28;

  public void setNewIssue28(String p)
  { 
    newIssue28 = p;
  }

  public String getNewIssue28()
  { 
    return newIssue28;
  }

  public void selectIssue28(ValueChangeEvent event)
  { 
    log.info("selectIssue28" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue28 = em.find(Issue.class, id);
      addIssue6(issue28);
    }
  }

  @DataModel("issue28List") private Map<String, String> issue28List;

  public Map<String, String> getIssue28List()
  { 
    return issue28List;
  }

  @Factory("issue28List") public void initIssue28List()
  { 
    log.info("initIssue28List");
    issue28List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue28List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson85;

  public void setNewPerson85(String p)
  { 
    newPerson85 = p;
  }

  public String getNewPerson85()
  { 
    return newPerson85;
  }

  public void selectPerson85(ValueChangeEvent event)
  { 
    log.info("selectPerson85" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person85 = em.find(Person.class, id);
      addPerson17(person85);
    }
  }

  @DataModel("person85List") private Map<String, String> person85List;

  public Map<String, String> getPerson85List()
  { 
    return person85List;
  }

  @Factory("person85List") public void initPerson85List()
  { 
    log.info("initPerson85List");
    person85List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person85List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1055List") private List<Person> person1055List;

  public List<Person> getPerson1055List()
  { 
    log.info("getPerson1055List");
    return person1055List;
  }

  @Factory("person1055List") public void initPerson1055List()
  { 
    log.info("initPerson1055List");
    person1055List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1155List") private List<ResearchProject> project1155List;

  public List<ResearchProject> getProject1155List()
  { 
    log.info("getProject1155List");
    return project1155List;
  }

  @Factory("project1155List") public void initProject1155List()
  { 
    log.info("initProject1155List");
    project1155List = em.createQuery("from " + "ResearchProject").getResultList();
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