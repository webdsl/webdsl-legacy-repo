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
    initPerson220List();
    initPerson112List();
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

  public void removePerson20(Person person219)
  { 
    this.getBug().getAssigned().remove(person219);
  }

  public void addPerson20(Person person219)
  { 
    this.getBug().getAssigned().add(person219);
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
    Issue issue35 = (Issue)event.getNewValue();
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

  private String newPerson220;

  public void setNewPerson220(String p)
  { 
    newPerson220 = p;
  }

  public String getNewPerson220()
  { 
    return newPerson220;
  }

  public void selectPerson220(ValueChangeEvent event)
  { 
    log.info("selectPerson220" + ": new value = " + " " + event.getNewValue());
    Person person220 = (Person)event.getNewValue();
  }

  @DataModel("person220List") private Map<String, String> person220List;

  public Map<String, String> getPerson220List()
  { 
    return person220List;
  }

  @Factory("person220List") public void initPerson220List()
  { 
    log.info("initPerson220List");
    person220List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person220List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person112List") private List<Person> person112List;

  public List<Person> getPerson112List()
  { 
    log.info("getPerson112List");
    return person112List;
  }

  @Factory("person112List") public void initPerson112List()
  { 
    log.info("initPerson112List");
    person112List = em.createQuery("from " + "Person").getResultList();
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