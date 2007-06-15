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
    Issue var63 = new Issue();
    issue = var63;
    initIssue27List();
    initPerson190List();
    initPerson99List();
    initProject89List();
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

  public void removePerson15(Person person189)
  { 
    this.getIssue().getAssigned().remove(person189);
  }

  public void addPerson15(Person person189)
  { 
    this.getIssue().getAssigned().add(person189);
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

  private String newPerson190;

  public void setNewPerson190(String p)
  { 
    newPerson190 = p;
  }

  public String getNewPerson190()
  { 
    return newPerson190;
  }

  public void selectPerson190(ValueChangeEvent event)
  { 
    log.info("selectPerson190" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person190 = em.find(Person.class, id);
      addPerson15(person190);
    }
  }

  @DataModel("person190List") private Map<String, String> person190List;

  public Map<String, String> getPerson190List()
  { 
    return person190List;
  }

  @Factory("person190List") public void initPerson190List()
  { 
    log.info("initPerson190List");
    person190List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person190List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person99List") private List<Person> person99List;

  public List<Person> getPerson99List()
  { 
    log.info("getPerson99List");
    return person99List;
  }

  @Factory("person99List") public void initPerson99List()
  { 
    log.info("initPerson99List");
    person99List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project89List") private List<ResearchProject> project89List;

  public List<ResearchProject> getProject89List()
  { 
    log.info("getProject89List");
    return project89List;
  }

  @Factory("project89List") public void initProject89List()
  { 
    log.info("initProject89List");
    project89List = em.createQuery("from " + "ResearchProject").getResultList();
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