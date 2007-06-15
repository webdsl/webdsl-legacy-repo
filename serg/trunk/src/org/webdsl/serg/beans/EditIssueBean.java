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
    initPerson183List();
    initPerson93List();
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

  public void removeIssue0(Issue issue14)
  { 
    this.getIssue().getIssues().remove(issue14);
  }

  public void addIssue0(Issue issue14)
  { 
    this.getIssue().getIssues().add(issue14);
  }

  public void removePerson14(Person person182)
  { 
    this.getIssue().getAssigned().remove(person182);
  }

  public void addPerson14(Person person182)
  { 
    this.getIssue().getAssigned().add(person182);
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

  private String newPerson183;

  public void setNewPerson183(String p)
  { 
    newPerson183 = p;
  }

  public String getNewPerson183()
  { 
    return newPerson183;
  }

  public void selectPerson183(ValueChangeEvent event)
  { 
    log.info("selectPerson183" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person183 = em.find(Person.class, id);
      addPerson14(person183);
    }
  }

  @DataModel("person183List") private Map<String, String> person183List;

  public Map<String, String> getPerson183List()
  { 
    return person183List;
  }

  @Factory("person183List") public void initPerson183List()
  { 
    log.info("initPerson183List");
    person183List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person183List.put(p.getName(), p.getId().toString());
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
}