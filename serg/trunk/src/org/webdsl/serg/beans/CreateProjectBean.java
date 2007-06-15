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
    Project var53 = new Project();
    project = var53;
    initIssue22List();
    initPerson190List();
    initPerson98List();
    initProject94List();
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

  public void removePerson17(Person person189)
  { 
    this.getProject().getAssigned().remove(person189);
  }

  public void addPerson17(Person person189)
  { 
    this.getProject().getAssigned().add(person189);
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
      addPerson17(person190);
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

  @DataModel("project94List") private List<ResearchProject> project94List;

  public List<ResearchProject> getProject94List()
  { 
    log.info("getProject94List");
    return project94List;
  }

  @Factory("project94List") public void initProject94List()
  { 
    log.info("initProject94List");
    project94List = em.createQuery("from " + "ResearchProject").getResultList();
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