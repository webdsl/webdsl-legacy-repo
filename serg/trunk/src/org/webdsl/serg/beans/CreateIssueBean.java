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
    Issue var38 = new Issue();
    issue = var38;
    initIssue15List();
    initPerson72List();
    initPerson1049List();
    initProject1149List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue1(Issue issue14)
  { 
    this.getIssue().getIssues().remove(issue14);
  }

  public void addIssue1(Issue issue14)
  { 
    this.getIssue().getIssues().add(issue14);
  }

  public void removePerson12(Person person71)
  { 
    this.getIssue().getAssigned().remove(person71);
  }

  public void addPerson12(Person person71)
  { 
    this.getIssue().getAssigned().add(person71);
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

  private String newIssue15;

  public void setNewIssue15(String p)
  { 
    newIssue15 = p;
  }

  public String getNewIssue15()
  { 
    return newIssue15;
  }

  public void selectIssue15(ValueChangeEvent event)
  { 
    log.info("selectIssue15" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue15 = em.find(Issue.class, id);
      addIssue1(issue15);
    }
  }

  @DataModel("issue15List") private Map<String, String> issue15List;

  public Map<String, String> getIssue15List()
  { 
    return issue15List;
  }

  @Factory("issue15List") public void initIssue15List()
  { 
    log.info("initIssue15List");
    issue15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue15List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson72;

  public void setNewPerson72(String p)
  { 
    newPerson72 = p;
  }

  public String getNewPerson72()
  { 
    return newPerson72;
  }

  public void selectPerson72(ValueChangeEvent event)
  { 
    log.info("selectPerson72" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person72 = em.find(Person.class, id);
      addPerson12(person72);
    }
  }

  @DataModel("person72List") private Map<String, String> person72List;

  public Map<String, String> getPerson72List()
  { 
    return person72List;
  }

  @Factory("person72List") public void initPerson72List()
  { 
    log.info("initPerson72List");
    person72List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person72List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1049List") private List<Person> person1049List;

  public List<Person> getPerson1049List()
  { 
    log.info("getPerson1049List");
    return person1049List;
  }

  @Factory("person1049List") public void initPerson1049List()
  { 
    log.info("initPerson1049List");
    person1049List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1149List") private List<ResearchProject> project1149List;

  public List<ResearchProject> getProject1149List()
  { 
    log.info("getProject1149List");
    return project1149List;
  }

  @Factory("project1149List") public void initProject1149List()
  { 
    log.info("initProject1149List");
    project1149List = em.createQuery("from " + "ResearchProject").getResultList();
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