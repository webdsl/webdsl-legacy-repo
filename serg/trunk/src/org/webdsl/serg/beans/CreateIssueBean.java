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
    Issue var52 = new Issue();
    issue = var52;
    initIssue17List();
    initPerson185List();
    initPerson94List();
    initProject89List();
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

  public void removePerson15(Person person184)
  { 
    this.getIssue().getAssigned().remove(person184);
  }

  public void addPerson15(Person person184)
  { 
    this.getIssue().getAssigned().add(person184);
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

  private String newPerson185;

  public void setNewPerson185(String p)
  { 
    newPerson185 = p;
  }

  public String getNewPerson185()
  { 
    return newPerson185;
  }

  public void selectPerson185(ValueChangeEvent event)
  { 
    log.info("selectPerson185" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person185 = em.find(Person.class, id);
      addPerson15(person185);
    }
  }

  @DataModel("person185List") private Map<String, String> person185List;

  public Map<String, String> getPerson185List()
  { 
    return person185List;
  }

  @Factory("person185List") public void initPerson185List()
  { 
    log.info("initPerson185List");
    person185List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person185List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person94List") private List<Person> person94List;

  public List<Person> getPerson94List()
  { 
    log.info("getPerson94List");
    return person94List;
  }

  @Factory("person94List") public void initPerson94List()
  { 
    log.info("initPerson94List");
    person94List = em.createQuery("from " + "Person").getResultList();
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