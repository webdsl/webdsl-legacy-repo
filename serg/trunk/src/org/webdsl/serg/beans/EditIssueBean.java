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
    initIssue25List();
    initPerson199List();
    initPerson98List();
    initProject88List();
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

  public void removeIssue0(Issue issue24)
  { 
    this.getIssue().getIssues().remove(issue24);
  }

  public void addIssue0(Issue issue24)
  { 
    this.getIssue().getIssues().add(issue24);
  }

  public void removePerson14(Person person198)
  { 
    this.getIssue().getAssigned().remove(person198);
  }

  public void addPerson14(Person person198)
  { 
    this.getIssue().getAssigned().add(person198);
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

  private String newIssue25;

  public void setNewIssue25(String p)
  { 
    newIssue25 = p;
  }

  public String getNewIssue25()
  { 
    return newIssue25;
  }

  public void selectIssue25(ValueChangeEvent event)
  { 
    log.info("selectIssue25" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue25 = em.find(Issue.class, id);
      addIssue0(issue25);
    }
  }

  @DataModel("issue25List") private Map<String, String> issue25List;

  public Map<String, String> getIssue25List()
  { 
    return issue25List;
  }

  @Factory("issue25List") public void initIssue25List()
  { 
    log.info("initIssue25List");
    issue25List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue25List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson199;

  public void setNewPerson199(String p)
  { 
    newPerson199 = p;
  }

  public String getNewPerson199()
  { 
    return newPerson199;
  }

  public void selectPerson199(ValueChangeEvent event)
  { 
    log.info("selectPerson199" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person199 = em.find(Person.class, id);
      addPerson14(person199);
    }
  }

  @DataModel("person199List") private Map<String, String> person199List;

  public Map<String, String> getPerson199List()
  { 
    return person199List;
  }

  @Factory("person199List") public void initPerson199List()
  { 
    log.info("initPerson199List");
    person199List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person199List.put(p.getName(), p.getId().toString());
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
}