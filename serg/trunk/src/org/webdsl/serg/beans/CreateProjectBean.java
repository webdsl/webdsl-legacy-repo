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
    Project var39 = new Project();
    project = var39;
    initIssue23List();
    initPerson80List();
    initPerson1052List();
    initProject1152List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeIssue4(Issue issue22)
  { 
    this.getProject().getIssues().remove(issue22);
  }

  public void addIssue4(Issue issue22)
  { 
    this.getProject().getIssues().add(issue22);
  }

  public void removePerson15(Person person79)
  { 
    this.getProject().getAssigned().remove(person79);
  }

  public void addPerson15(Person person79)
  { 
    this.getProject().getAssigned().add(person79);
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

  private String newIssue23;

  public void setNewIssue23(String p)
  { 
    newIssue23 = p;
  }

  public String getNewIssue23()
  { 
    return newIssue23;
  }

  public void selectIssue23(ValueChangeEvent event)
  { 
    log.info("selectIssue23" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue23 = em.find(Issue.class, id);
      addIssue4(issue23);
    }
  }

  @DataModel("issue23List") private Map<String, String> issue23List;

  public Map<String, String> getIssue23List()
  { 
    return issue23List;
  }

  @Factory("issue23List") public void initIssue23List()
  { 
    log.info("initIssue23List");
    issue23List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue23List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson80;

  public void setNewPerson80(String p)
  { 
    newPerson80 = p;
  }

  public String getNewPerson80()
  { 
    return newPerson80;
  }

  public void selectPerson80(ValueChangeEvent event)
  { 
    log.info("selectPerson80" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person80 = em.find(Person.class, id);
      addPerson15(person80);
    }
  }

  @DataModel("person80List") private Map<String, String> person80List;

  public Map<String, String> getPerson80List()
  { 
    return person80List;
  }

  @Factory("person80List") public void initPerson80List()
  { 
    log.info("initPerson80List");
    person80List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person80List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1052List") private List<Person> person1052List;

  public List<Person> getPerson1052List()
  { 
    log.info("getPerson1052List");
    return person1052List;
  }

  @Factory("person1052List") public void initPerson1052List()
  { 
    log.info("initPerson1052List");
    person1052List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1152List") private List<ResearchProject> project1152List;

  public List<ResearchProject> getProject1152List()
  { 
    log.info("getProject1152List");
    return project1152List;
  }

  @Factory("project1152List") public void initProject1152List()
  { 
    log.info("initProject1152List");
    project1152List = em.createQuery("from " + "ResearchProject").getResultList();
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