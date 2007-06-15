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
    Project var65 = new Project();
    project = var65;
    initIssue26List();
    initPerson194List();
    initPerson104List();
    initProject93List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue3(Issue issue25)
  { 
    this.getProject().getIssues().remove(issue25);
  }

  public void addIssue3(Issue issue25)
  { 
    this.getProject().getIssues().add(issue25);
  }

  public void removePerson17(Person person193)
  { 
    this.getProject().getAssigned().remove(person193);
  }

  public void addPerson17(Person person193)
  { 
    this.getProject().getAssigned().add(person193);
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

  private String newIssue26;

  public void setNewIssue26(String p)
  { 
    newIssue26 = p;
  }

  public String getNewIssue26()
  { 
    return newIssue26;
  }

  public void selectIssue26(ValueChangeEvent event)
  { 
    log.info("selectIssue26" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue26 = em.find(Issue.class, id);
      addIssue3(issue26);
    }
  }

  @DataModel("issue26List") private Map<String, String> issue26List;

  public Map<String, String> getIssue26List()
  { 
    return issue26List;
  }

  @Factory("issue26List") public void initIssue26List()
  { 
    log.info("initIssue26List");
    issue26List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue26List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson194;

  public void setNewPerson194(String p)
  { 
    newPerson194 = p;
  }

  public String getNewPerson194()
  { 
    return newPerson194;
  }

  public void selectPerson194(ValueChangeEvent event)
  { 
    log.info("selectPerson194" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person194 = em.find(Person.class, id);
      addPerson17(person194);
    }
  }

  @DataModel("person194List") private Map<String, String> person194List;

  public Map<String, String> getPerson194List()
  { 
    return person194List;
  }

  @Factory("person194List") public void initPerson194List()
  { 
    log.info("initPerson194List");
    person194List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person194List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person104List") private List<Person> person104List;

  public List<Person> getPerson104List()
  { 
    log.info("getPerson104List");
    return person104List;
  }

  @Factory("person104List") public void initPerson104List()
  { 
    log.info("initPerson104List");
    person104List = em.createQuery("from " + "Person").getResultList();
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