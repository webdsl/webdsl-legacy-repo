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
    initPerson198List();
    initPerson106List();
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

  public void removeIssue4(Issue issue34)
  { 
    this.getBug().getIssues().remove(issue34);
  }

  public void addIssue4(Issue issue34)
  { 
    this.getBug().getIssues().add(issue34);
  }

  public void removePerson18(Person person197)
  { 
    this.getBug().getAssigned().remove(person197);
  }

  public void addPerson18(Person person197)
  { 
    this.getBug().getAssigned().add(person197);
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

  private String newPerson198;

  public void setNewPerson198(String p)
  { 
    newPerson198 = p;
  }

  public String getNewPerson198()
  { 
    return newPerson198;
  }

  public void selectPerson198(ValueChangeEvent event)
  { 
    log.info("selectPerson198" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person198 = em.find(Person.class, id);
      addPerson18(person198);
    }
  }

  @DataModel("person198List") private Map<String, String> person198List;

  public Map<String, String> getPerson198List()
  { 
    return person198List;
  }

  @Factory("person198List") public void initPerson198List()
  { 
    log.info("initPerson198List");
    person198List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person198List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person106List") private List<Person> person106List;

  public List<Person> getPerson106List()
  { 
    log.info("getPerson106List");
    return person106List;
  }

  @Factory("person106List") public void initPerson106List()
  { 
    log.info("initPerson106List");
    person106List = em.createQuery("from " + "Person").getResultList();
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