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
    Issue var50 = new Issue();
    issue = var50;
    initIssue17List();
    initPerson167List();
    initPerson93List();
    initProject88List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue1(Issue issue16)
  { 
    this.getIssue().getIssues().remove(issue16);
  }

  public void addIssue1(Issue issue16)
  { 
    this.getIssue().getIssues().add(issue16);
  }

  public void removePerson15(Person person166)
  { 
    this.getIssue().getAssigned().remove(person166);
  }

  public void addPerson15(Person person166)
  { 
    this.getIssue().getAssigned().add(person166);
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

  private String newIssue17;

  public void setNewIssue17(String p)
  { 
    newIssue17 = p;
  }

  public String getNewIssue17()
  { 
    return newIssue17;
  }

  public void selectIssue17(ValueChangeEvent event)
  { 
    log.info("selectIssue17" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue17 = em.find(Issue.class, id);
      addIssue1(issue17);
    }
  }

  @DataModel("issue17List") private Map<String, String> issue17List;

  public Map<String, String> getIssue17List()
  { 
    return issue17List;
  }

  @Factory("issue17List") public void initIssue17List()
  { 
    log.info("initIssue17List");
    issue17List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue17List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson167;

  public void setNewPerson167(String p)
  { 
    newPerson167 = p;
  }

  public String getNewPerson167()
  { 
    return newPerson167;
  }

  public void selectPerson167(ValueChangeEvent event)
  { 
    log.info("selectPerson167" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person167 = em.find(Person.class, id);
      addPerson15(person167);
    }
  }

  @DataModel("person167List") private Map<String, String> person167List;

  public Map<String, String> getPerson167List()
  { 
    return person167List;
  }

  @Factory("person167List") public void initPerson167List()
  { 
    log.info("initPerson167List");
    person167List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person167List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person93List") private List<Person> person93List;

  public List<Person> getPerson93List()
  { 
    log.info("getPerson93List");
    return person93List;
  }

  @Factory("person93List") public void initPerson93List()
  { 
    log.info("initPerson93List");
    person93List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project88List") private List<ResearchProject> project88List;

  public List<ResearchProject> getProject88List()
  { 
    log.info("getProject88List");
    return project88List;
  }

  @Factory("project88List") public void initProject88List()
  { 
    log.info("initProject88List");
    project88List = em.createQuery("from " + "ResearchProject").getResultList();
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