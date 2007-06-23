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
    Project var73 = new Project();
    project = var73;
    initIssue32List();
    initPerson217List();
    initPerson108List();
    initProject98List();
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

  public void removePerson19(Person person216)
  { 
    this.getProject().getAssigned().remove(person216);
  }

  public void addPerson19(Person person216)
  { 
    this.getProject().getAssigned().add(person216);
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
    Issue issue32 = (Issue)event.getNewValue();
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

  private String newPerson217;

  public void setNewPerson217(String p)
  { 
    newPerson217 = p;
  }

  public String getNewPerson217()
  { 
    return newPerson217;
  }

  public void selectPerson217(ValueChangeEvent event)
  { 
    log.info("selectPerson217" + ": new value = " + " " + event.getNewValue());
    Person person217 = (Person)event.getNewValue();
  }

  @DataModel("person217List") private Map<String, String> person217List;

  public Map<String, String> getPerson217List()
  { 
    return person217List;
  }

  @Factory("person217List") public void initPerson217List()
  { 
    log.info("initPerson217List");
    person217List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person217List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person108List") private List<Person> person108List;

  public List<Person> getPerson108List()
  { 
    log.info("getPerson108List");
    return person108List;
  }

  @Factory("person108List") public void initPerson108List()
  { 
    log.info("initPerson108List");
    person108List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project98List") private List<ResearchProject> project98List;

  public List<ResearchProject> getProject98List()
  { 
    log.info("getProject98List");
    return project98List;
  }

  @Factory("project98List") public void initProject98List()
  { 
    log.info("initProject98List");
    project98List = em.createQuery("from " + "ResearchProject").getResultList();
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