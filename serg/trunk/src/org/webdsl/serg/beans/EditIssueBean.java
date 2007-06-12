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
    initIssue15List();
    initPerson186List();
    initPerson91List();
    initProject86List();
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

  public void removeIssue0(Issue issue14)
  { 
    this.getIssue().getIssues().remove(issue14);
  }

  public void addIssue0(Issue issue14)
  { 
    this.getIssue().getIssues().add(issue14);
  }

  public void removePerson18(Person person185)
  { 
    this.getIssue().getAssigned().remove(person185);
  }

  public void addPerson18(Person person185)
  { 
    this.getIssue().getAssigned().add(person185);
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
      addIssue0(issue15);
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

  private String newPerson186;

  public void setNewPerson186(String p)
  { 
    newPerson186 = p;
  }

  public String getNewPerson186()
  { 
    return newPerson186;
  }

  public void selectPerson186(ValueChangeEvent event)
  { 
    log.info("selectPerson186" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person186 = em.find(Person.class, id);
      addPerson18(person186);
    }
  }

  @DataModel("person186List") private Map<String, String> person186List;

  public Map<String, String> getPerson186List()
  { 
    return person186List;
  }

  @Factory("person186List") public void initPerson186List()
  { 
    log.info("initPerson186List");
    person186List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person186List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person91List") private List<Person> person91List;

  public List<Person> getPerson91List()
  { 
    log.info("getPerson91List");
    return person91List;
  }

  @Factory("person91List") public void initPerson91List()
  { 
    log.info("initPerson91List");
    person91List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project86List") private List<ResearchProject> project86List;

  public List<ResearchProject> getProject86List()
  { 
    log.info("getProject86List");
    return project86List;
  }

  @Factory("project86List") public void initProject86List()
  { 
    log.info("initProject86List");
    project86List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}