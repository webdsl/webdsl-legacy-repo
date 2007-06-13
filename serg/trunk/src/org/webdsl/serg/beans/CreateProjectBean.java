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

@Stateful @Name("createProject") public class CreateProjectBean  implements CreateProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createProject" + ".initalize()");
    Project var51 = new Project();
    project = var51;
    initIssue22List();
    initPerson172List();
    initPerson97List();
    initProject93List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue3(Issue issue21)
  { 
    this.getProject().getIssues().remove(issue21);
  }

  public void addIssue3(Issue issue21)
  { 
    this.getProject().getIssues().add(issue21);
  }

  public void removePerson17(Person person171)
  { 
    this.getProject().getAssigned().remove(person171);
  }

  public void addPerson17(Person person171)
  { 
    this.getProject().getAssigned().add(person171);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getProject());
    return "/" + "viewProject" + ".seam?" + ("project" + "=" + project.getId() + "");
  }

  private String newIssue22;

  public void setNewIssue22(String p)
  { 
    newIssue22 = p;
  }

  public String getNewIssue22()
  { 
    return newIssue22;
  }

  public void selectIssue22(ValueChangeEvent event)
  { 
    log.info("selectIssue22" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue22 = em.find(Issue.class, id);
      addIssue3(issue22);
    }
  }

  @DataModel("issue22List") private Map<String, String> issue22List;

  public Map<String, String> getIssue22List()
  { 
    return issue22List;
  }

  @Factory("issue22List") public void initIssue22List()
  { 
    log.info("initIssue22List");
    issue22List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue22List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson172;

  public void setNewPerson172(String p)
  { 
    newPerson172 = p;
  }

  public String getNewPerson172()
  { 
    return newPerson172;
  }

  public void selectPerson172(ValueChangeEvent event)
  { 
    log.info("selectPerson172" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person172 = em.find(Person.class, id);
      addPerson17(person172);
    }
  }

  @DataModel("person172List") private Map<String, String> person172List;

  public Map<String, String> getPerson172List()
  { 
    return person172List;
  }

  @Factory("person172List") public void initPerson172List()
  { 
    log.info("initPerson172List");
    person172List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person172List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person97List") private List<Person> person97List;

  public List<Person> getPerson97List()
  { 
    log.info("getPerson97List");
    return person97List;
  }

  @Factory("person97List") public void initPerson97List()
  { 
    log.info("initPerson97List");
    person97List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project93List") private List<ResearchProject> project93List;

  public List<ResearchProject> getProject93List()
  { 
    log.info("getProject93List");
    return project93List;
  }

  @Factory("project93List") public void initProject93List()
  { 
    log.info("initProject93List");
    project93List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Project project;

  public Project getProject()
  { 
    log.info("getProject");
    return project;
  }

  public void setProject(Project project)
  { 
    log.info("setProject");
    this.project = project;
  }
}