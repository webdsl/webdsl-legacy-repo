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
    Project var68 = new Project();
    project = var68;
    initIssue32List();
    initPerson205List();
    initPerson103List();
    initProject94List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue3(Issue issue31)
  { 
    this.getProject().getIssues().remove(issue31);
  }

  public void addIssue3(Issue issue31)
  { 
    this.getProject().getIssues().add(issue31);
  }

  public void removePerson17(Person person204)
  { 
    this.getProject().getAssigned().remove(person204);
  }

  public void addPerson17(Person person204)
  { 
    this.getProject().getAssigned().add(person204);
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

  private String newIssue32;

  public void setNewIssue32(String p)
  { 
    newIssue32 = p;
  }

  public String getNewIssue32()
  { 
    return newIssue32;
  }

  public void selectIssue32(ValueChangeEvent event)
  { 
    log.info("selectIssue32" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue32 = em.find(Issue.class, id);
      addIssue3(issue32);
    }
  }

  @DataModel("issue32List") private Map<String, String> issue32List;

  public Map<String, String> getIssue32List()
  { 
    return issue32List;
  }

  @Factory("issue32List") public void initIssue32List()
  { 
    log.info("initIssue32List");
    issue32List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue32List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson205;

  public void setNewPerson205(String p)
  { 
    newPerson205 = p;
  }

  public String getNewPerson205()
  { 
    return newPerson205;
  }

  public void selectPerson205(ValueChangeEvent event)
  { 
    log.info("selectPerson205" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person205 = em.find(Person.class, id);
      addPerson17(person205);
    }
  }

  @DataModel("person205List") private Map<String, String> person205List;

  public Map<String, String> getPerson205List()
  { 
    return person205List;
  }

  @Factory("person205List") public void initPerson205List()
  { 
    log.info("initPerson205List");
    person205List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person205List.put(p.getName(), p.getId().toString());
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