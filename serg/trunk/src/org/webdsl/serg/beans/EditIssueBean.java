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
    initPerson209List();
    initPerson103List();
    initProject92List();
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

  public void removePerson16(Person person208)
  { 
    this.getIssue().getAssigned().remove(person208);
  }

  public void addPerson16(Person person208)
  { 
    this.getIssue().getAssigned().add(person208);
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
    Issue issue25 = (Issue)event.getNewValue();
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

  private String newPerson209;

  public void setNewPerson209(String p)
  { 
    newPerson209 = p;
  }

  public String getNewPerson209()
  { 
    return newPerson209;
  }

  public void selectPerson209(ValueChangeEvent event)
  { 
    log.info("selectPerson209" + ": new value = " + " " + event.getNewValue());
    Person person209 = (Person)event.getNewValue();
  }

  @DataModel("person209List") private Map<String, String> person209List;

  public Map<String, String> getPerson209List()
  { 
    return person209List;
  }

  @Factory("person209List") public void initPerson209List()
  { 
    log.info("initPerson209List");
    person209List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person209List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person103List") private List<Person> person103List;

  public List<Person> getPerson103List()
  { 
    log.info("getPerson103List");
    return person103List;
  }

  @Factory("person103List") public void initPerson103List()
  { 
    log.info("initPerson103List");
    person103List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project92List") private List<ResearchProject> project92List;

  public List<ResearchProject> getProject92List()
  { 
    log.info("getProject92List");
    return project92List;
  }

  @Factory("project92List") public void initProject92List()
  { 
    log.info("initProject92List");
    project92List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}