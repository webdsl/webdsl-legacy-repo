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
    initIssue13List();
    initPerson70List();
    initPerson1048List();
    initProject1148List();
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

  public void removeIssue0(Issue issue12)
  { 
    this.getIssue().getIssues().remove(issue12);
  }

  public void addIssue0(Issue issue12)
  { 
    this.getIssue().getIssues().add(issue12);
  }

  public void removePerson11(Person person69)
  { 
    this.getIssue().getAssigned().remove(person69);
  }

  public void addPerson11(Person person69)
  { 
    this.getIssue().getAssigned().add(person69);
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

  private String newIssue13;

  public void setNewIssue13(String p)
  { 
    newIssue13 = p;
  }

  public String getNewIssue13()
  { 
    return newIssue13;
  }

  public void selectIssue13(ValueChangeEvent event)
  { 
    log.info("selectIssue13" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue13 = em.find(Issue.class, id);
      addIssue0(issue13);
    }
  }

  @DataModel("issue13List") private Map<String, String> issue13List;

  public Map<String, String> getIssue13List()
  { 
    return issue13List;
  }

  @Factory("issue13List") public void initIssue13List()
  { 
    log.info("initIssue13List");
    issue13List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue13List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson70;

  public void setNewPerson70(String p)
  { 
    newPerson70 = p;
  }

  public String getNewPerson70()
  { 
    return newPerson70;
  }

  public void selectPerson70(ValueChangeEvent event)
  { 
    log.info("selectPerson70" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person70 = em.find(Person.class, id);
      addPerson11(person70);
    }
  }

  @DataModel("person70List") private Map<String, String> person70List;

  public Map<String, String> getPerson70List()
  { 
    return person70List;
  }

  @Factory("person70List") public void initPerson70List()
  { 
    log.info("initPerson70List");
    person70List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person70List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1048List") private List<Person> person1048List;

  public List<Person> getPerson1048List()
  { 
    log.info("getPerson1048List");
    return person1048List;
  }

  @Factory("person1048List") public void initPerson1048List()
  { 
    log.info("initPerson1048List");
    person1048List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1148List") private List<ResearchProject> project1148List;

  public List<ResearchProject> getProject1148List()
  { 
    log.info("getProject1148List");
    return project1148List;
  }

  @Factory("project1148List") public void initProject1148List()
  { 
    log.info("initProject1148List");
    project1148List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}