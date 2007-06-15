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
    initIssue25List();
    initPerson193List();
    initPerson101List();
    initProject101List();
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

  public void removeIssue4(Issue issue24)
  { 
    this.getBug().getIssues().remove(issue24);
  }

  public void addIssue4(Issue issue24)
  { 
    this.getBug().getIssues().add(issue24);
  }

  public void removePerson18(Person person192)
  { 
    this.getBug().getAssigned().remove(person192);
  }

  public void addPerson18(Person person192)
  { 
    this.getBug().getAssigned().add(person192);
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

  private String newIssue25;

  public void setNewIssue25(String p)
  { 
    newIssue25 = p;
  }

  public String getNewIssue25()
  { 
    return newIssue25;
  }

  public void selectIssue25(ValueChangeEvent event)
  { 
    log.info("selectIssue25" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue25 = em.find(Issue.class, id);
      addIssue4(issue25);
    }
  }

  @DataModel("issue25List") private Map<String, String> issue25List;

  public Map<String, String> getIssue25List()
  { 
    return issue25List;
  }

  @Factory("issue25List") public void initIssue25List()
  { 
    log.info("initIssue25List");
    issue25List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue25List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson193;

  public void setNewPerson193(String p)
  { 
    newPerson193 = p;
  }

  public String getNewPerson193()
  { 
    return newPerson193;
  }

  public void selectPerson193(ValueChangeEvent event)
  { 
    log.info("selectPerson193" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person193 = em.find(Person.class, id);
      addPerson18(person193);
    }
  }

  @DataModel("person193List") private Map<String, String> person193List;

  public Map<String, String> getPerson193List()
  { 
    return person193List;
  }

  @Factory("person193List") public void initPerson193List()
  { 
    log.info("initPerson193List");
    person193List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person193List.put(p.getName(), p.getId().toString());
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
}