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
    initIssue29List();
    initPerson197List();
    initPerson108List();
    initProject100List();
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

  public void removeIssue4(Issue issue28)
  { 
    this.getBug().getIssues().remove(issue28);
  }

  public void addIssue4(Issue issue28)
  { 
    this.getBug().getIssues().add(issue28);
  }

  public void removePerson18(Person person196)
  { 
    this.getBug().getAssigned().remove(person196);
  }

  public void addPerson18(Person person196)
  { 
    this.getBug().getAssigned().add(person196);
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

  private String newIssue29;

  public void setNewIssue29(String p)
  { 
    newIssue29 = p;
  }

  public String getNewIssue29()
  { 
    return newIssue29;
  }

  public void selectIssue29(ValueChangeEvent event)
  { 
    log.info("selectIssue29" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue29 = em.find(Issue.class, id);
      addIssue4(issue29);
    }
  }

  @DataModel("issue29List") private Map<String, String> issue29List;

  public Map<String, String> getIssue29List()
  { 
    return issue29List;
  }

  @Factory("issue29List") public void initIssue29List()
  { 
    log.info("initIssue29List");
    issue29List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue29List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson197;

  public void setNewPerson197(String p)
  { 
    newPerson197 = p;
  }

  public String getNewPerson197()
  { 
    return newPerson197;
  }

  public void selectPerson197(ValueChangeEvent event)
  { 
    log.info("selectPerson197" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person197 = em.find(Person.class, id);
      addPerson18(person197);
    }
  }

  @DataModel("person197List") private Map<String, String> person197List;

  public Map<String, String> getPerson197List()
  { 
    return person197List;
  }

  @Factory("person197List") public void initPerson197List()
  { 
    log.info("initPerson197List");
    person197List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person197List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person108List") private List<Person> person108List;

  public List<Person> getPerson108List()
  { 
    log.info("getPerson108List");
    return person108List;
  }

  @Factory("person108List") public void initPerson108List()
  { 
    log.info("initPerson108List");
    person108List = em.createQuery("from " + "Person").getResultList();
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
}