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

@Stateful @Name("editBug") public class EditBugBean  implements EditBugBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editBug" + ".initalize()");
    if(bugId == null)
    { 
      log.info("No " + "bugId" + " defined, creating new " + "Bug");
      bug = new Bug();
    }
    else
    { 
      bug = em.find(Bug.class, bugId);
    }
    initIssue28List();
    initPerson199List();
    initPerson99List();
    initProject99List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("bug") private Long bugId;

  private Bug bug;

  public void setBug(Bug bug)
  { 
    log.info("setBug");
    this.bug = bug;
  }

  public Bug getBug()
  { 
    log.info("getBug");
    return bug;
  }

  public void removeIssue5(Issue issue27)
  { 
    this.getBug().getIssues().remove(issue27);
  }

  public void addIssue5(Issue issue27)
  { 
    this.getBug().getIssues().add(issue27);
  }

  public void removePerson23(Person person198)
  { 
    this.getBug().getAssigned().remove(person198);
  }

  public void addPerson23(Person person198)
  { 
    this.getBug().getAssigned().add(person198);
  }

  @End public String cancel()
  { 
    return "/" + "viewBug" + ".seam?" + ("bug" + "=" + bug.getId() + "");
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
      addIssue5(issue28);
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
      addPerson23(person199);
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

  @DataModel("person99List") private List<Person> person99List;

  public List<Person> getPerson99List()
  { 
    log.info("getPerson99List");
    return person99List;
  }

  @Factory("person99List") public void initPerson99List()
  { 
    log.info("initPerson99List");
    person99List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project99List") private List<ResearchProject> project99List;

  public List<ResearchProject> getProject99List()
  { 
    log.info("getProject99List");
    return project99List;
  }

  @Factory("project99List") public void initProject99List()
  { 
    log.info("initProject99List");
    project99List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}