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
    initPerson188List();
    initPerson92List();
    initProject87List();
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

  public void removePerson19(Person person187)
  { 
    this.getIssue().getAssigned().remove(person187);
  }

  public void addPerson19(Person person187)
  { 
    this.getIssue().getAssigned().add(person187);
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

  private String newPerson188;

  public void setNewPerson188(String p)
  { 
    newPerson188 = p;
  }

  public String getNewPerson188()
  { 
    return newPerson188;
  }

  public void selectPerson188(ValueChangeEvent event)
  { 
    log.info("selectPerson188" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person188 = em.find(Person.class, id);
      addPerson19(person188);
    }
  }

  @DataModel("person188List") private Map<String, String> person188List;

  public Map<String, String> getPerson188List()
  { 
    return person188List;
  }

  @Factory("person188List") public void initPerson188List()
  { 
    log.info("initPerson188List");
    person188List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person188List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person92List") private List<Person> person92List;

  public List<Person> getPerson92List()
  { 
    log.info("getPerson92List");
    return person92List;
  }

  @Factory("person92List") public void initPerson92List()
  { 
    log.info("initPerson92List");
    person92List = em.createQuery("from " + "Person").getResultList();
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