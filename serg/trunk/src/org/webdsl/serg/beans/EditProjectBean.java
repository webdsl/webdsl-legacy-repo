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

@Stateful @Name("editProject") public class EditProjectBean  implements EditProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editProject" + ".initalize()");
    if(projectId == null)
    { 
      log.info("No " + "projectId" + " defined, creating new " + "Project");
      project = new Project();
    }
    else
    { 
      project = em.find(Project.class, projectId);
    }
    initIssue21List();
    initPerson78List();
    initPerson1051List();
    initProject1151List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("project") private Long projectId;

  private Project project;

  public void setProject(Project project)
  { 
    log.info("setProject");
    this.project = project;
  }

  public Project getProject()
  { 
    log.info("getProject");
    return project;
  }

  public void removeIssue3(Issue issue20)
  { 
    this.getProject().getIssues().remove(issue20);
  }

  public void addIssue3(Issue issue20)
  { 
    this.getProject().getIssues().add(issue20);
  }

  public void removePerson14(Person person77)
  { 
    this.getProject().getAssigned().remove(person77);
  }

  public void addPerson14(Person person77)
  { 
    this.getProject().getAssigned().add(person77);
  }

  @End public String cancel()
  { 
    return "/" + "viewProject" + ".seam?" + ("project" + "=" + project.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getProject());
    return "/" + "viewProject" + ".seam?" + ("project" + "=" + project.getId() + "");
  }

  private String newIssue21;

  public void setNewIssue21(String p)
  { 
    newIssue21 = p;
  }

  public String getNewIssue21()
  { 
    return newIssue21;
  }

  public void selectIssue21(ValueChangeEvent event)
  { 
    log.info("selectIssue21" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue21 = em.find(Issue.class, id);
      addIssue3(issue21);
    }
  }

  @DataModel("issue21List") private Map<String, String> issue21List;

  public Map<String, String> getIssue21List()
  { 
    return issue21List;
  }

  @Factory("issue21List") public void initIssue21List()
  { 
    log.info("initIssue21List");
    issue21List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue21List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson78;

  public void setNewPerson78(String p)
  { 
    newPerson78 = p;
  }

  public String getNewPerson78()
  { 
    return newPerson78;
  }

  public void selectPerson78(ValueChangeEvent event)
  { 
    log.info("selectPerson78" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person78 = em.find(Person.class, id);
      addPerson14(person78);
    }
  }

  @DataModel("person78List") private Map<String, String> person78List;

  public Map<String, String> getPerson78List()
  { 
    return person78List;
  }

  @Factory("person78List") public void initPerson78List()
  { 
    log.info("initPerson78List");
    person78List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person78List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1051List") private List<Person> person1051List;

  public List<Person> getPerson1051List()
  { 
    log.info("getPerson1051List");
    return person1051List;
  }

  @Factory("person1051List") public void initPerson1051List()
  { 
    log.info("initPerson1051List");
    person1051List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1151List") private List<ResearchProject> project1151List;

  public List<ResearchProject> getProject1151List()
  { 
    log.info("getProject1151List");
    return project1151List;
  }

  @Factory("project1151List") public void initProject1151List()
  { 
    log.info("initProject1151List");
    project1151List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}