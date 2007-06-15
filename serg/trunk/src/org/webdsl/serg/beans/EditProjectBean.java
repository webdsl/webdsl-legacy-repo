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
    initIssue24List();
    initPerson192List();
    initPerson103List();
    initProject91List();
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

  public void removeIssue2(Issue issue23)
  { 
    this.getProject().getIssues().remove(issue23);
  }

  public void addIssue2(Issue issue23)
  { 
    this.getProject().getIssues().add(issue23);
  }

  public void removePerson16(Person person191)
  { 
    this.getProject().getAssigned().remove(person191);
  }

  public void addPerson16(Person person191)
  { 
    this.getProject().getAssigned().add(person191);
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

  private String newIssue24;

  public void setNewIssue24(String p)
  { 
    newIssue24 = p;
  }

  public String getNewIssue24()
  { 
    return newIssue24;
  }

  public void selectIssue24(ValueChangeEvent event)
  { 
    log.info("selectIssue24" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue24 = em.find(Issue.class, id);
      addIssue2(issue24);
    }
  }

  @DataModel("issue24List") private Map<String, String> issue24List;

  public Map<String, String> getIssue24List()
  { 
    return issue24List;
  }

  @Factory("issue24List") public void initIssue24List()
  { 
    log.info("initIssue24List");
    issue24List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue24List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson192;

  public void setNewPerson192(String p)
  { 
    newPerson192 = p;
  }

  public String getNewPerson192()
  { 
    return newPerson192;
  }

  public void selectPerson192(ValueChangeEvent event)
  { 
    log.info("selectPerson192" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person192 = em.find(Person.class, id);
      addPerson16(person192);
    }
  }

  @DataModel("person192List") private Map<String, String> person192List;

  public Map<String, String> getPerson192List()
  { 
    return person192List;
  }

  @Factory("person192List") public void initPerson192List()
  { 
    log.info("initPerson192List");
    person192List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person192List.put(p.getName(), p.getId().toString());
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

  @DataModel("project91List") private List<ResearchProject> project91List;

  public List<ResearchProject> getProject91List()
  { 
    log.info("getProject91List");
    return project91List;
  }

  @Factory("project91List") public void initProject91List()
  { 
    log.info("initProject91List");
    project91List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}