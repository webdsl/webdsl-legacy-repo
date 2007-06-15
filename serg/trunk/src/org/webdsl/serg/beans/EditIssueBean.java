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

@Stateful @Name("editIssue") public class EditIssueBean  implements EditIssueBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editIssue" + ".initalize()");
    if(issueId == null)
    { 
      log.info("No " + "issueId" + " defined, creating new " + "Issue");
      issue = new Issue();
    }
    else
    { 
      issue = em.find(Issue.class, issueId);
    }
    initIssue19List();
    initPerson187List();
    initPerson98List();
    initProject87List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("issue") private Long issueId;

  private Issue issue;

  public void setIssue(Issue issue)
  { 
    log.info("setIssue");
    this.issue = issue;
  }

  public Issue getIssue()
  { 
    log.info("getIssue");
    return issue;
  }

  public void removeIssue0(Issue issue18)
  { 
    this.getIssue().getIssues().remove(issue18);
  }

  public void addIssue0(Issue issue18)
  { 
    this.getIssue().getIssues().add(issue18);
  }

  public void removePerson14(Person person186)
  { 
    this.getIssue().getAssigned().remove(person186);
  }

  public void addPerson14(Person person186)
  { 
    this.getIssue().getAssigned().add(person186);
  }

  @End public String cancel()
  { 
    return "/" + "viewIssue" + ".seam?" + ("issue" + "=" + issue.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getIssue());
    return "/" + "viewIssue" + ".seam?" + ("issue" + "=" + issue.getId() + "");
  }

  private String newIssue19;

  public void setNewIssue19(String p)
  { 
    newIssue19 = p;
  }

  public String getNewIssue19()
  { 
    return newIssue19;
  }

  public void selectIssue19(ValueChangeEvent event)
  { 
    log.info("selectIssue19" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue19 = em.find(Issue.class, id);
      addIssue0(issue19);
    }
  }

  @DataModel("issue19List") private Map<String, String> issue19List;

  public Map<String, String> getIssue19List()
  { 
    return issue19List;
  }

  @Factory("issue19List") public void initIssue19List()
  { 
    log.info("initIssue19List");
    issue19List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue19List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson187;

  public void setNewPerson187(String p)
  { 
    newPerson187 = p;
  }

  public String getNewPerson187()
  { 
    return newPerson187;
  }

  public void selectPerson187(ValueChangeEvent event)
  { 
    log.info("selectPerson187" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person187 = em.find(Person.class, id);
      addPerson14(person187);
    }
  }

  @DataModel("person187List") private Map<String, String> person187List;

  public Map<String, String> getPerson187List()
  { 
    return person187List;
  }

  @Factory("person187List") public void initPerson187List()
  { 
    log.info("initPerson187List");
    person187List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person187List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person98List") private List<Person> person98List;

  public List<Person> getPerson98List()
  { 
    log.info("getPerson98List");
    return person98List;
  }

  @Factory("person98List") public void initPerson98List()
  { 
    log.info("initPerson98List");
    person98List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project87List") private List<ResearchProject> project87List;

  public List<ResearchProject> getProject87List()
  { 
    log.info("getProject87List");
    return project87List;
  }

  @Factory("project87List") public void initProject87List()
  { 
    log.info("initProject87List");
    project87List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}