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
    initIssue26List();
    initPerson83List();
    initPerson1054List();
    initProject1154List();
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

  public void removeIssue5(Issue issue25)
  { 
    this.getBug().getIssues().remove(issue25);
  }

  public void addIssue5(Issue issue25)
  { 
    this.getBug().getIssues().add(issue25);
  }

  public void removePerson16(Person person82)
  { 
    this.getBug().getAssigned().remove(person82);
  }

  public void addPerson16(Person person82)
  { 
    this.getBug().getAssigned().add(person82);
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

  private String newIssue26;

  public void setNewIssue26(String p)
  { 
    newIssue26 = p;
  }

  public String getNewIssue26()
  { 
    return newIssue26;
  }

  public void selectIssue26(ValueChangeEvent event)
  { 
    log.info("selectIssue26" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue26 = em.find(Issue.class, id);
      addIssue5(issue26);
    }
  }

  @DataModel("issue26List") private Map<String, String> issue26List;

  public Map<String, String> getIssue26List()
  { 
    return issue26List;
  }

  @Factory("issue26List") public void initIssue26List()
  { 
    log.info("initIssue26List");
    issue26List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue26List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson83;

  public void setNewPerson83(String p)
  { 
    newPerson83 = p;
  }

  public String getNewPerson83()
  { 
    return newPerson83;
  }

  public void selectPerson83(ValueChangeEvent event)
  { 
    log.info("selectPerson83" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person83 = em.find(Person.class, id);
      addPerson16(person83);
    }
  }

  @DataModel("person83List") private Map<String, String> person83List;

  public Map<String, String> getPerson83List()
  { 
    return person83List;
  }

  @Factory("person83List") public void initPerson83List()
  { 
    log.info("initPerson83List");
    person83List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person83List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1054List") private List<Person> person1054List;

  public List<Person> getPerson1054List()
  { 
    log.info("getPerson1054List");
    return person1054List;
  }

  @Factory("person1054List") public void initPerson1054List()
  { 
    log.info("initPerson1054List");
    person1054List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1154List") private List<ResearchProject> project1154List;

  public List<ResearchProject> getProject1154List()
  { 
    log.info("getProject1154List");
    return project1154List;
  }

  @Factory("project1154List") public void initProject1154List()
  { 
    log.info("initProject1154List");
    project1154List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}