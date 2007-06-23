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

@Stateful @Name("createIssue") public class CreateIssueBean  implements CreateIssueBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createIssue" + ".initalize()");
    Issue var70 = new Issue();
    issue = var70;
    initIssue27List();
    initPerson212List();
    initPerson104List();
    initProject93List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue1(Issue issue26)
  { 
    this.getIssue().getIssues().remove(issue26);
  }

  public void addIssue1(Issue issue26)
  { 
    this.getIssue().getIssues().add(issue26);
  }

  public void removePerson17(Person person211)
  { 
    this.getIssue().getAssigned().remove(person211);
  }

  public void addPerson17(Person person211)
  { 
    this.getIssue().getAssigned().add(person211);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getIssue());
    return "/" + "viewIssue" + ".seam?" + ("issue" + "=" + issue.getId() + "");
  }

  private String newIssue27;

  public void setNewIssue27(String p)
  { 
    newIssue27 = p;
  }

  public String getNewIssue27()
  { 
    return newIssue27;
  }

  public void selectIssue27(ValueChangeEvent event)
  { 
    log.info("selectIssue27" + ": new value = " + " " + event.getNewValue());
    Issue issue27 = (Issue)event.getNewValue();
  }

  @DataModel("issue27List") private Map<String, String> issue27List;

  public Map<String, String> getIssue27List()
  { 
    return issue27List;
  }

  @Factory("issue27List") public void initIssue27List()
  { 
    log.info("initIssue27List");
    issue27List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue27List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson212;

  public void setNewPerson212(String p)
  { 
    newPerson212 = p;
  }

  public String getNewPerson212()
  { 
    return newPerson212;
  }

  public void selectPerson212(ValueChangeEvent event)
  { 
    log.info("selectPerson212" + ": new value = " + " " + event.getNewValue());
    Person person212 = (Person)event.getNewValue();
  }

  @DataModel("person212List") private Map<String, String> person212List;

  public Map<String, String> getPerson212List()
  { 
    return person212List;
  }

  @Factory("person212List") public void initPerson212List()
  { 
    log.info("initPerson212List");
    person212List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person212List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person104List") private List<Person> person104List;

  public List<Person> getPerson104List()
  { 
    log.info("getPerson104List");
    return person104List;
  }

  @Factory("person104List") public void initPerson104List()
  { 
    log.info("initPerson104List");
    person104List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project93List") private List<ResearchProject> project93List;

  public List<ResearchProject> getProject93List()
  { 
    log.info("getProject93List");
    return project93List;
  }

  @Factory("project93List") public void initProject93List()
  { 
    log.info("initProject93List");
    project93List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Issue issue;

  public Issue getIssue()
  { 
    log.info("getIssue");
    return issue;
  }

  public void setIssue(Issue issue)
  { 
    log.info("setIssue");
    this.issue = issue;
  }
}