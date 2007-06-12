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
    initIssue25List();
    initPerson196List();
    initPerson96List();
    initProject92List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue4(Issue issue24)
  { 
    this.getProject().getIssues().remove(issue24);
  }

  public void addIssue4(Issue issue24)
  { 
    this.getProject().getIssues().add(issue24);
  }

  public void removePerson22(Person person195)
  { 
    this.getProject().getAssigned().remove(person195);
  }

  public void addPerson22(Person person195)
  { 
    this.getProject().getAssigned().add(person195);
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
      addIssue4(issue25);
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

  private String newPerson196;

  public void setNewPerson196(String p)
  { 
    newPerson196 = p;
  }

  public String getNewPerson196()
  { 
    return newPerson196;
  }

  public void selectPerson196(ValueChangeEvent event)
  { 
    log.info("selectPerson196" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person196 = em.find(Person.class, id);
      addPerson22(person196);
    }
  }

  @DataModel("person196List") private Map<String, String> person196List;

  public Map<String, String> getPerson196List()
  { 
    return person196List;
  }

  @Factory("person196List") public void initPerson196List()
  { 
    log.info("initPerson196List");
    person196List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person196List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person96List") private List<Person> person96List;

  public List<Person> getPerson96List()
  { 
    log.info("getPerson96List");
    return person96List;
  }

  @Factory("person96List") public void initPerson96List()
  { 
    log.info("initPerson96List");
    person96List = em.createQuery("from " + "Person").getResultList();
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