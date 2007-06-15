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
    Issue var62 = new Issue();
    issue = var62;
    initIssue21List();
    initPerson189List();
    initPerson99List();
    initProject88List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue1(Issue issue20)
  { 
    this.getIssue().getIssues().remove(issue20);
  }

  public void addIssue1(Issue issue20)
  { 
    this.getIssue().getIssues().add(issue20);
  }

  public void removePerson15(Person person188)
  { 
    this.getIssue().getAssigned().remove(person188);
  }

  public void addPerson15(Person person188)
  { 
    this.getIssue().getAssigned().add(person188);
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

  private String newIssue21;

  public void setNewIssue21(String p)
  { 
    newIssue21 = p;
  }

  public String getNewIssue21()
  { 
    return newIssue21;
  }

  public void selectIssue21(ValueChangeEvent event)
  { 
    log.info("selectIssue21" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue21 = em.find(Issue.class, id);
      addIssue1(issue21);
    }
  }

  @DataModel("issue21List") private Map<String, String> issue21List;

  public Map<String, String> getIssue21List()
  { 
    return issue21List;
  }

  @Factory("issue21List") public void initIssue21List()
  { 
    log.info("initIssue21List");
    issue21List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue21List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson189;

  public void setNewPerson189(String p)
  { 
    newPerson189 = p;
  }

  public String getNewPerson189()
  { 
    return newPerson189;
  }

  public void selectPerson189(ValueChangeEvent event)
  { 
    log.info("selectPerson189" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person189 = em.find(Person.class, id);
      addPerson15(person189);
    }
  }

  @DataModel("person189List") private Map<String, String> person189List;

  public Map<String, String> getPerson189List()
  { 
    return person189List;
  }

  @Factory("person189List") public void initPerson189List()
  { 
    log.info("initPerson189List");
    person189List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person189List.put(p.getName(), p.getId().toString());
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