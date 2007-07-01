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
    initPerson226List();
    initPerson106List();
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

  public void removePerson17(Person person225)
  { 
    this.getIssue().getAssigned().remove(person225);
  }

  public void addPerson17(Person person225)
  { 
    this.getIssue().getAssigned().add(person225);
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue27 = em.find(Issue.class, id);
      addIssue1(issue27);
    }
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

  private String newPerson226;

  public void setNewPerson226(String p)
  { 
    newPerson226 = p;
  }

  public String getNewPerson226()
  { 
    return newPerson226;
  }

  public void selectPerson226(ValueChangeEvent event)
  { 
    log.info("selectPerson226" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person226 = em.find(Person.class, id);
      addPerson17(person226);
    }
  }

  @DataModel("person226List") private Map<String, String> person226List;

  public Map<String, String> getPerson226List()
  { 
    return person226List;
  }

  @Factory("person226List") public void initPerson226List()
  { 
    log.info("initPerson226List");
    person226List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person226List.put(p.getName(), p.getId().toString());
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