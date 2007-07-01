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
    initIssue35List();
    initPerson234List();
    initPerson114List();
    initProject105List();
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

  public void removeIssue4(Issue issue34)
  { 
    this.getBug().getIssues().remove(issue34);
  }

  public void addIssue4(Issue issue34)
  { 
    this.getBug().getIssues().add(issue34);
  }

  public void removePerson20(Person person233)
  { 
    this.getBug().getAssigned().remove(person233);
  }

  public void addPerson20(Person person233)
  { 
    this.getBug().getAssigned().add(person233);
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

  private String newIssue35;

  public void setNewIssue35(String p)
  { 
    newIssue35 = p;
  }

  public String getNewIssue35()
  { 
    return newIssue35;
  }

  public void selectIssue35(ValueChangeEvent event)
  { 
    log.info("selectIssue35" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue35 = em.find(Issue.class, id);
      addIssue4(issue35);
    }
  }

  @DataModel("issue35List") private Map<String, String> issue35List;

  public Map<String, String> getIssue35List()
  { 
    return issue35List;
  }

  @Factory("issue35List") public void initIssue35List()
  { 
    log.info("initIssue35List");
    issue35List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue35List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson234;

  public void setNewPerson234(String p)
  { 
    newPerson234 = p;
  }

  public String getNewPerson234()
  { 
    return newPerson234;
  }

  public void selectPerson234(ValueChangeEvent event)
  { 
    log.info("selectPerson234" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person234 = em.find(Person.class, id);
      addPerson20(person234);
    }
  }

  @DataModel("person234List") private Map<String, String> person234List;

  public Map<String, String> getPerson234List()
  { 
    return person234List;
  }

  @Factory("person234List") public void initPerson234List()
  { 
    log.info("initPerson234List");
    person234List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person234List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person114List") private List<Person> person114List;

  public List<Person> getPerson114List()
  { 
    log.info("getPerson114List");
    return person114List;
  }

  @Factory("person114List") public void initPerson114List()
  { 
    log.info("initPerson114List");
    person114List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project105List") private List<ResearchProject> project105List;

  public List<ResearchProject> getProject105List()
  { 
    log.info("getProject105List");
    return project105List;
  }

  @Factory("project105List") public void initProject105List()
  { 
    log.info("initProject105List");
    project105List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}